package com.xiaoxin.WeiLanOJ.judge;
import com.xiaoxin.WeiLanOJ.model.entity.QuestionSubmit;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 **/
public interface JudgeService {
    /*判题服务
    *  @param questionSubmitId
     */
    QuestionSubmit doJudge(long questionSubmitId);

}
