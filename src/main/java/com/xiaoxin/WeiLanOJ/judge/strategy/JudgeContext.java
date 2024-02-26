package com.xiaoxin.WeiLanOJ.judge.strategy;

import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionJudgeCase;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;
import com.xiaoxin.WeiLanOJ.model.entity.Question;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 * 上下文
 * 定义在策略中需要传递的参数
 **/
@Data

public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<String> inputList;
    private List<String> outputList;
    private Question question;
    List<QuestionJudgeCase> judgeCaseList;
    private QuestionSubmit questionSubmit;
}
