package com.example.web.util.container;

import com.example.web.model.oauth.JwtUser;

public class SessionContainer {

    private SessionContainer() {
    }

    private static final ThreadLocal<JwtUser> sessionThreadLocal = new ThreadLocal<>();

    public static void setSession(JwtUser jwtUser) {
        if (jwtUser == null) {
            return;
        }

        clearSession();
        sessionThreadLocal.set(jwtUser);
    }

    public static void clearSession() {
        sessionThreadLocal.remove();
    }

    public static JwtUser getSession() {
        return sessionThreadLocal.get();
    }
}
