package com.xiaoxin.WeiLanOJ.judge.codesandbox.impl;

import com.xiaoxin.WeiLanOJ.judge.codesandbox.CodeSandbox;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeRequest;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeResponse;

/**
 * @author:XIAOXIN
 * @date:2024/02/25
 * 调用远程接口的代码沙箱（为了保存代码流程）
 **/
public class ThildCodeSandbox implements CodeSandbox {

    @Override
    public ExcuteCodeResponse excuteCode(ExcuteCodeRequest excuteCodeRequest) {
        System.out.println("第三方平台代码沙箱");
        return null;
    }
}
