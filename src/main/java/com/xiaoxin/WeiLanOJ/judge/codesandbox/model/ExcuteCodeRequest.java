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
public class ExcuteCodeRequest {
    private List<String> inList;
    private String code;
    private String language;
}
