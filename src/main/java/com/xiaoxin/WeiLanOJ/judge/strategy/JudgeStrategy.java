package com.xiaoxin.WeiLanOJ.judge.strategy;

import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 * 设计模式
 * 策略模式
 **/
public interface JudgeStrategy {
    /*
    * 执行判题的接口
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
