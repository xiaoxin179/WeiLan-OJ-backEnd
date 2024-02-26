package com.xiaoxin.WeiLanOJ.judge.codesandbox;

import com.xiaoxin.WeiLanOJ.judge.codesandbox.impl.ExanmpleCodeSandbox;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.impl.RemoteCodeSandbox;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.impl.ThildCodeSandbox;

/**
 * @author:XIAOXIN
 * @date:2024/02/25
 * 设计模式
 * 工厂模式
 * 代码沙箱工厂
 **/
public class CodeSandboxFanctory {
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExanmpleCodeSandbox();
            case "remote":
                return  new RemoteCodeSandbox();
            case "thild":
                return new ThildCodeSandbox();
            default:
                return new ExanmpleCodeSandbox();
        }

    }

}
