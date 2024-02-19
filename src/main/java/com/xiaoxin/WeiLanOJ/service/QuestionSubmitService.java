package com.xiaoxin.WeiLanOJ.service;

import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.WeiLanOJ.model.entity.User;

/**
* @author XIAOXIN
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-02-18 18:08:14
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmit:题目提交信息
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmit, User loginUser);
}
