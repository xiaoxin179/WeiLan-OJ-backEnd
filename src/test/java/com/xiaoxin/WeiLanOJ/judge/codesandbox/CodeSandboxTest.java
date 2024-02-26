package com.xiaoxin.WeiLanOJ.judge.codesandbox;

import com.xiaoxin.WeiLanOJ.judge.codesandbox.impl.ExanmpleCodeSandbox;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeRequest;
import com.xiaoxin.WeiLanOJ.judge.codesandbox.model.ExcuteCodeResponse;
import com.xiaoxin.WeiLanOJ.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class CodeSandboxTest {
    @Value("${codesandbox.type:example}")
    private String type;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            CodeSandbox codeSandbox = CodeSandboxFanctory.newInstance(type);
            String code = "int main() { }";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3 4");
            ExcuteCodeRequest executeCodeRequest = ExcuteCodeRequest.builder()
                    .code(code)
                    .language(language)
                    .inList(inputList)
                    .build();
            ExcuteCodeResponse executeCodeResponse = codeSandbox.excuteCode(executeCodeRequest);
        }
    }

    @Test
    void excuteCodeByValue() {
        System.out.println("传递进来的type:" + type);
        CodeSandbox codeSandbox = CodeSandboxFanctory.newInstance(type);
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExcuteCodeRequest executeCodeRequest = ExcuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inList(inputList)
                .build();
        ExcuteCodeResponse executeCodeResponse = codeSandbox.excuteCode(executeCodeRequest);
    }
    @Test
    void excuteCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFanctory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 4");
        ExcuteCodeRequest executeCodeRequest = ExcuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inList(inputList)
                .build();
        ExcuteCodeResponse executeCodeResponse = codeSandbox.excuteCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }


}