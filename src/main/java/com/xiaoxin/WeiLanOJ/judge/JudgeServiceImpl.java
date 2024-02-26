package com.xiaoxin.WeiLanOJ.judge;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.xiaoxin.WeiLanOJ.common.ErrorCode;
import com.xiaoxin.WeiLanOJ.exception.BusinessException;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.CodeSandbox;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.CodeSandboxFanctory;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.CodeSandboxProxy;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeRequest;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeResponse;
import com.xiaoxin.WeiLanOJ.judge.strategy.DefaultJudgeStrategy;
import com.xiaoxin.WeiLanOJ.judge.strategy.JavaLanguageJudgeStrategy;
import com.xiaoxin.WeiLanOJ.judge.strategy.JudgeContext;
import com.xiaoxin.WeiLanOJ.judge.strategy.JudgeStrategy;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionJudgeCase;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionJudgeConfig;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;
import com.xiaoxin.WeiLanOJ.model.entity.Question;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitJudgeInfoMessageEnum;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitLanguageEnum;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitStatusEnum;
import com.xiaoxin.WeiLanOJ.model.vo.QuestionSubmitVO;
import com.xiaoxin.WeiLanOJ.service.QuestionService;
import com.xiaoxin.WeiLanOJ.service.QuestionSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 **/

@Service
@Slf4j
public class JudgeServiceImpl implements JudgeService {
    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionSubmitService questionSubmitService;
    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;
    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "这个题目不存在");
        }
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在检测中");
        }
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
//        调用代码沙箱，查看执行结果
        CodeSandbox codeSandbox = CodeSandboxFanctory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        String judgeCaseStr = question.getJudgeCase();
        List<QuestionJudgeCase> questionJudgeCaseList = JSONUtil.toList(judgeCaseStr, QuestionJudgeCase.class);
        List<String> inputList = questionJudgeCaseList.stream().map(QuestionJudgeCase::getInput).collect(Collectors.toList());
        ExcuteCodeRequest executeCodeRequest = ExcuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inList(inputList)
                .build();
        ExcuteCodeResponse executeCodeResponse = codeSandbox.excuteCode(executeCodeRequest);
//        判断代码沙箱的执行结果
//        (1)判断输入输出
        List<String> outList = executeCodeResponse.getOutList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(questionJudgeCaseList);
        judgeContext.setQuestionSubmit(questionSubmit);
//        直接调用

        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
//        修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.ACCEPTED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitService.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新失败");
        }
        return questionSubmitService.getById(questionId);
    }
}
