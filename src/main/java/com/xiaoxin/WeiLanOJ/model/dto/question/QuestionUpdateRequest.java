package com.xiaoxin.WeiLanOJ.model.dto.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 更新请求(给管理员用的1）
 */
@Data
public class QuestionUpdateRequest implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 判题用例（json 数组）
     */
    private List<QuestionJudgeCase> judgeCase;
    /**
     * 判题配置（json 对象）
     */
    private QuestionJudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}