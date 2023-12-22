package com.enterprise.manage.dto;

import lombok.Data;

@Data
public class LoginForm {
    private String userName;

    private String password;
    /**验证码*/
    private String randomCode;
    private String uuid;





}
