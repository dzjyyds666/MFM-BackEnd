package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.vo.login.CaptchaVo;
import com.Aaron.MFM.web.app.vo.login.LoginInfoVo;
import com.Aaron.MFM.web.app.vo.user.UserInfoVo;
import com.Aaron.MFM.web.app.vo.user.createUserVo;
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

     CaptchaVo getCaptcha(String type);

     String login(LoginInfoVo loginInfoVo);

     void logout();

     void register(createUserVo userinfo);

    UserInfoVo getUserInfo();

    void updateInfo(UserInfo userinfo);
}
