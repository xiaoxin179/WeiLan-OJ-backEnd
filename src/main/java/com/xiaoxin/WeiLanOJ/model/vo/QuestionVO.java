package com.xiaoxin.WeiLanOJ.model.vo;
import cn.hutool.json.JSONUtil;
import com.xiaoxin.WeiLanOJ.model.dto.question.QuestionJudgeConfig;
import com.xiaoxin.WeiLanOJ.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目封装类
 * 提供给前端来使用
 */
@Data
public class QuestionVO implements Serializable {
    /**
     * id
     */
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
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置（json 对象）
     */
    private QuestionJudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建题目人的信息
     * 把Vo转换为原生的java对象
     * 下述两个方法就是实现对象和包装类之间的转化
     */
    private UserVO userVO;
    public static Question voToObj(QuestionVO questionVO) {
        if (questionVO == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<String> tagList = questionVO.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));

        }
        QuestionJudgeConfig judgeConfigVO = questionVO.getJudgeConfig();
        if (judgeConfigVO != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfigVO));
        }
        return question;
    }

    /**
     * 对象转包装类
     *json字符串转换为Vo对象
     * @param question
     * @return
     */
    public static QuestionVO objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        List<String> tagsList = JSONUtil.toList(question.getTags(), String.class);
        questionVO.setTags(tagsList);
        String judgeConfigStr = question.getJudgeConfig();
        questionVO.setJudgeConfig(JSONUtil.toBean(judgeConfigStr, QuestionJudgeConfig.class));

        return questionVO;
    }

    private static final long serialVersionUID = 1L;
}