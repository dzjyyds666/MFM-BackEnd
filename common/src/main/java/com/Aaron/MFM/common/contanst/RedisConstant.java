package com.Aaron.MFM.common.contanst;

public class RedisConstant {
    public static final String ADMIN_LOGIN_PREFIX = "admin:login:";
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;

    public static final Integer TOKEN_TTL_SEC = 60 * 60 * 24 * 3 ;

    public static final String APP_LOGIN_PREFIX = "app:login:";
    public static final String APP_CREATE_PREFIX = "app:create:";
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;
    public static final Integer APP_CREATE_CODE_RESEND_TIME_SEC = 60;
    
}