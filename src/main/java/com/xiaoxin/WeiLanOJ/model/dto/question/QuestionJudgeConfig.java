package com.xiaoxin.WeiLanOJ.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 */
@Data
public class QuestionJudgeConfig {
 /**
 * 时间限制（ms）
 */
 private Long timeLimit;
 /**
 * 内存限制(kb)
 */
 private Long memoryLimit;
 /**
  * 堆栈限制(kb)
  */
 private Long stakeLimit;

}