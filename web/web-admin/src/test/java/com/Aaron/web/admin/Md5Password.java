package com.Aaron.web.admin;

import com.Aaron.MFM.common.utils.JWTutils;
import org.apache.commons.codec.digest.DigestUtils;

public class Md5Password {

    public static void main(String[] args) {
        String password = "1433223qq";
        System.out.println(DigestUtils.md5Hex(password));
    }
}
