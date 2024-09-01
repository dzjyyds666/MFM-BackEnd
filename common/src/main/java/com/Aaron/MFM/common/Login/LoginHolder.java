package com.Aaron.MFM.common.Login;

import com.Aaron.MFM.model.entity.UserInfo;

public class LoginHolder {

    private static final ThreadLocal<UserInfo> Threadlocal = new ThreadLocal<>();

    public static void setLoginUser(UserInfo userInfo) {
        Threadlocal.set(userInfo);
    }

    public static UserInfo getLoginUser() {
        return Threadlocal.get();
    }

    public static void remove() {
        Threadlocal.remove();
    }
}
