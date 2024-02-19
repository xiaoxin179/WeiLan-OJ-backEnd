package com.xiaoxin.WeiLanOJ.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionQueryRequest;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.xiaoxin.WeiLanOJ.model.entity.Question;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.WeiLanOJ.model.entity.User;
import com.xiaoxin.WeiLanOJ.model.vo.QuestionSubmitVO;
import com.xiaoxin.WeiLanOJ.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

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
    /**
     * 获取查询条件
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionPage, User loginUser);
}
