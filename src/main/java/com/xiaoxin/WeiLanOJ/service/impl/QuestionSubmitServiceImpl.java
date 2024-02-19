package com.xiaoxin.WeiLanOJ.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.WeiLanOJ.common.ErrorCode;
import com.xiaoxin.WeiLanOJ.exception.BusinessException;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaoxin.WeiLanOJ.model.entity.*;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitLanguageEnum;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitStatusEnum;
import com.xiaoxin.WeiLanOJ.service.QuestionService;
import com.xiaoxin.WeiLanOJ.service.QuestionSubmitService;
import com.xiaoxin.WeiLanOJ.mapper.QuestionSubmitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author XIAOXIN
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2024-02-18 18:08:14
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService{
    @Resource
    private QuestionService questionService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //检验编程语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        // 判断实体是否存在，根据类别获取实体
        long questionId = questionSubmitAddRequest.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已提交题目
        long userId = loginUser.getId();
        // 每个用户串行提交题目
        // 锁必须要包裹住事务方法
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setLanguage(language);
        questionSubmit.setCode(questionSubmitAddRequest.getCode());
//        设置初始状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入失败");
        }
        return questionSubmit.getId();
    }

}




