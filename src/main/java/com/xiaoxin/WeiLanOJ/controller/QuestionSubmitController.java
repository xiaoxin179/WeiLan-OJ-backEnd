package com.xiaoxin.WeiLanOJ.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoxin.WeiLanOJ.annotation.AuthCheck;
import com.xiaoxin.WeiLanOJ.common.BaseResponse;
import com.xiaoxin.WeiLanOJ.common.ErrorCode;
import com.xiaoxin.WeiLanOJ.common.ResultUtils;
import com.xiaoxin.WeiLanOJ.constant.UserConstant;
import com.xiaoxin.WeiLanOJ.exception.BusinessException;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionQueryRequest;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.xiaoxin.WeiLanOJ.model.entity.Question;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import com.xiaoxin.WeiLanOJ.model.entity.User;
import com.xiaoxin.WeiLanOJ.model.vo.QuestionSubmitVO;
import com.xiaoxin.WeiLanOJ.service.QuestionSubmitService;
import com.xiaoxin.WeiLanOJ.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 题目提交接口
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 本次点赞变化数
     */
    @PostMapping("/")
    public BaseResponse<Long> doSubmitQuestion(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long  questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
//        从数据库中查询到了原始的提交信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
//        返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage,loginUser));
    }

}
