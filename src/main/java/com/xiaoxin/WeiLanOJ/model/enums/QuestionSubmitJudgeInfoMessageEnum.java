package com.xiaoxin.WeiLanOJ.model.enums;
import org.apache.commons.lang3.ObjectUtils;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 判题信息消息枚举
 */
public enum QuestionSubmitJudgeInfoMessageEnum {

    ACCEPTED("Accepted", "成功"),
    WRONG_ANSWER("Wrong Answer", "答案错误"),
    COMPILE_ERROR("Compile Error", "编译错误"),
    MEMORY_LIMIT_EXCEEDED("MemoryLimit Exceeded", "内存溢出"),
    TIME_LIMIT_EXCEEDED("TimeLimit Exceeded", "超时"),
    PRESENTATION_ERROR("Presentation Error", "展示错误"),
    OUTPUT_LIMIT_EXCEEDED("OutputLimit Exceeded", "输出溢出"),
    WAITING("Waiting", "等待中"),
    DANGEROUS_OPERATION("Dangerous Operation", "危险操作"),
    RUNTIME_ERROR("Runtime error", "运行错误"),
    SYSTEM_ERROR("system error", "系统错误");

    private final String text;

    private final String value;

    QuestionSubmitJudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitJudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitJudgeInfoMessageEnum anEnum : QuestionSubmitJudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
