package com.xiaoxin.WeiLanOJ.judge.codesandbox;

import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeRequest;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:XIAOXIN
 * @date:2024/02/26
 * 使用代理模式增强原有的代理类
 **/
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {
    private final CodeSandbox codeSandbox;
    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }


    @Override
    public ExcuteCodeResponse excuteCode(ExcuteCodeRequest excuteCodeRequest) {
        log.info("判题机的请求信息：" + excuteCodeRequest.toString());
        ExcuteCodeResponse excuteCodeResponse = codeSandbox.excuteCode(excuteCodeRequest);
        log.info("判题机的响应信息："+excuteCodeResponse);
        return excuteCodeResponse;
    }

}
