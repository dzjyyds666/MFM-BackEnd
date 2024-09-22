package com.Aaron.MFM.web.app.vo.user;

import lombok.Data;

@Data
public class createUserVo {
    private String phone;
    private String avaterUrl;
    private String password;
    private String nickname;
    private String captchaCode;
    private String captcahKey;
    private String city;
    private String province;
}
