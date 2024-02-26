package com.xiaoxin.WeiLanOJ.judge;

import com.xiaoxin.WeiLanOJ.judge.strategy.DefaultJudgeStrategy;
import com.xiaoxin.WeiLanOJ.judge.strategy.JavaLanguageJudgeStrategy;
import com.xiaoxin.WeiLanOJ.judge.strategy.JudgeContext;
import com.xiaoxin.WeiLanOJ.judge.strategy.JudgeStrategy;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 * 判题管理（简化调用）
 **/
@Component
public class JudgeManager {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
