package com.xiaoxin.WeiLanOJ.model.dto.user;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户登录请求
 *
 * @author <a href="https://github.com/lixiaoxin">程序员xiaoxin</a>
 * @from <a href="https://xiaoxin.icu">编程导航知识星球</a>
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
