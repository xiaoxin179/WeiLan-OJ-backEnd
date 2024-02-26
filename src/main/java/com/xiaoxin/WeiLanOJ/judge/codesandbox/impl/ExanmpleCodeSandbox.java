package com.xiaoxin.WeiLanOJ.judge.codesandbox.impl;

import com.xiaoxin.WeiLanOJ.judge.codesandbox.CodeSandbox;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeRequest;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeResponse;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitJudgeInfoMessageEnum;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @author:XIAOXIN
 * @date:2024/02/25
 * 为了跑通程序的假的代码沙箱
 **/
public class ExanmpleCodeSandbox implements CodeSandbox {
    @Override
    public ExcuteCodeResponse excuteCode(ExcuteCodeRequest excuteCodeRequest) {
        ExcuteCodeResponse excuteCodeResponse = new ExcuteCodeResponse();
        List<String> inList = excuteCodeRequest.getInList();
        String code = excuteCodeRequest.getCode();
        String language = excuteCodeRequest.getLanguage();
        excuteCodeResponse.setOutList(inList);
        excuteCodeResponse.setCode(code);
        excuteCodeResponse.setLanguage(language);
        excuteCodeResponse.setStatus(QuestionSubmitStatusEnum.ACCEPTED.getValue());
        excuteCodeResponse.setMessage("测试执行成功");
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(QuestionSubmitJudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);
        excuteCodeResponse.setJudgeInfo(judgeInfo);
        return null;
    }
}
