package com.xiaoxin.WeiLanOJ.judge.codesandbox.model;
import com.xiaoxin.WeiLanOJ.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author:XIAOXIN
 * @date:2024/02/25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcuteCodeResponse {
    private List<String> outList;
    private String code;
    private String language;
    private Integer status;
    private String message;
//    判题信息
    private JudgeInfo judgeInfo;
}
