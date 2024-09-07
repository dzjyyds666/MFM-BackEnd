package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.vo.login.CaptchaVo;
import com.Aaron.MFM.web.app.vo.login.LoginInfoVo;
import com.Aaron.MFM.web.app.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface IUserInfoService extends IService<UserInfo> {

     CaptchaVo getCaptcha();

     String login(LoginInfoVo loginInfoVo);

     void logout();

     void register(UserInfo userinfo);

    UserInfoVo getUserInfo();

    void updateInfo(UserInfo userinfo);
}
