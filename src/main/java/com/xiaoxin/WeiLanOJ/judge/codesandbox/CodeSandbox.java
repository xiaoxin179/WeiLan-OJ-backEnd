package com.xiaoxin.WeiLanOJ.judge.codesandbox;

import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeRequest;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeResponse;

/**
 * @author:XIAOXIN
 * @date:2024/02/25
 * 代码沙箱的接口定义
 **/
public interface CodeSandbox {
    ExcuteCodeResponse excuteCode(ExcuteCodeRequest excuteCodeRequest);
}
