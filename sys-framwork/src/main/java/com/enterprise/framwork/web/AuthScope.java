package com.enterprise.framwork.web;

/**
 * @author chenyi
 */
public class AuthScope {
    private static final ScopeKey<String> LOGIN_USER = new ScopeKey<>();
//    private static final ScopeKey<String>   = new ScopeKey<>();

    public static String getLoginUser() {
        return LOGIN_USER.get();
    }

    public static void setLoginUser(String loginUser) {
        if (loginUser == null) {
            loginUser = "unknownUser";
        }
        LOGIN_USER.set(loginUser);
    }
}
