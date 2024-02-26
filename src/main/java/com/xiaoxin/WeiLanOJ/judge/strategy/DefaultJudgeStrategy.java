package com.xiaoxin.WeiLanOJ.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionJudgeCase;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionJudgeConfig;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;
import com.xiaoxin.WeiLanOJ.model.entity.Question;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitJudgeInfoMessageEnum;

import java.util.List;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 * 默认策略
 **/
public class DefaultJudgeStrategy implements JudgeStrategy {
    /*
    *执行判题
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();
        List<String> inputList = judgeContext.getInputList();
        List<String> outList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<QuestionJudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();

        List<QuestionJudgeCase> questionJudgeCaseList = judgeContext.getJudgeCaseList();
        QuestionSubmitJudgeInfoMessageEnum judgeInfoMessageEnum = QuestionSubmitJudgeInfoMessageEnum.ACCEPTED;
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setTime(time);
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        if (outList.size() != inputList.size()) {
            judgeInfoMessageEnum = QuestionSubmitJudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        for (int i = 0; i < questionJudgeCaseList.size(); i++) {
            QuestionJudgeCase judgeCase = questionJudgeCaseList.get(i);
            if (!judgeCase.getOutput().equals(outList.get(i))) {
                judgeInfoMessageEnum = QuestionSubmitJudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
//        （2）判断限制条件
//        从代码沙箱的响应结果中获取实际执行信息
        String judgeConfigStr = question.getJudgeConfig();
        QuestionJudgeConfig questionJudgeConfig = JSONUtil.toBean(judgeConfigStr, QuestionJudgeConfig.class);
//        获取题目的时间和空间限制
        Long rightTimeLimit = questionJudgeConfig.getTimeLimit();
        Long rightMemoryLimit = questionJudgeConfig.getMemoryLimit();
        if (memory > rightMemoryLimit) {
            judgeInfoMessageEnum = QuestionSubmitJudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        if (time > rightTimeLimit) {
            judgeInfoMessageEnum = QuestionSubmitJudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        return judgeInfoResponse;
    }
}
