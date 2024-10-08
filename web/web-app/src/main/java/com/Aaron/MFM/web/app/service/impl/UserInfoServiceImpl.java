package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.contanst.RedisConstant;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.common.utils.JWTutils;
import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.mapper.CityInfoMapper;
import com.Aaron.MFM.web.app.mapper.UserInfoMapper;
import com.Aaron.MFM.web.app.service.IUserInfoService;
import com.Aaron.MFM.web.app.vo.login.CaptchaVo;
import com.Aaron.MFM.web.app.vo.login.LoginInfoVo;
import com.Aaron.MFM.web.app.vo.user.UserInfoVo;
import com.Aaron.MFM.web.app.vo.user.createUserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.catalina.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CityInfoMapper cityInfoMapper;

    @Override
    public CaptchaVo getCaptcha(String type) {
        // 定义验证码对象
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        // 设置类型
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 获取验证码 全部是小写
        String code = specCaptcha.text().toLowerCase();
        String image = specCaptcha.toBase64();
        // 生成key
        String key = null;
        if(type.equals(RedisConstant.ADMIN_LOGIN_PREFIX)){
            key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        } else if (type.equals(RedisConstant.APP_LOGIN_PREFIX)) {
            key = RedisConstant.APP_LOGIN_PREFIX + UUID.randomUUID();
        }else if (type.equals(RedisConstant.APP_CREATE_PREFIX)){
            key = RedisConstant.APP_CREATE_PREFIX + UUID.randomUUID();
        }
        if (key == null){
            throw new MFMException(201,"获取验证码失败");
        }
        redisTemplate.opsForValue().set(key,code,RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new CaptchaVo(image,key);
    }

    @Override
    public String login(LoginInfoVo loginInfoVo) {

        if(StringUtils.hasText(loginInfoVo.getCaptchaCode()) == false){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        String code = redisTemplate.opsForValue().get(loginInfoVo.getCaptchaKey());
        if(code == null){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        if(!redisTemplate.opsForValue().get(loginInfoVo.getCaptchaKey()).equals(code)){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        if(StringUtils.hasText(loginInfoVo.getPhone()) == false){
            throw new MFMException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone,loginInfoVo.getPhone());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        if(userInfo == null){
            throw new MFMException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        if (userInfo.getStatus() == 1){
            throw new MFMException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        if(StringUtils.hasText(loginInfoVo.getPassword()) == false){
            throw new MFMException(ResultCodeEnum.APP_LOGIN_PASSWORD_EMPTY);
        }
        if(!userInfo.getPassword().equals(DigestUtils.md5Hex(loginInfoVo.getPassword()))){
            throw new MFMException(ResultCodeEnum.APP_LOGIN_PASSWORD_ERROR);
        }
        String token = JWTutils.createToken(userInfo.getId(),userInfo.getRole());
        redisTemplate.opsForValue().set(RedisConstant.ADMIN_LOGIN_PREFIX + userInfo.getId(),token,RedisConstant.TOKEN_TTL_SEC,TimeUnit.SECONDS);

        return token;
    }

    @Override
    public void logout() {
        UserInfo loginUser = LoginHolder.getLoginUser();
        redisTemplate.delete(RedisConstant.ADMIN_LOGIN_PREFIX + loginUser.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void register(createUserVo userinfo) {
        // 验证码验证
        if(StringUtils.hasText(userinfo.getCaptchaCode()) == false){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        String code = redisTemplate.opsForValue().get(userinfo.getCaptcahKey());
        if(code == null){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }
        if(!redisTemplate.opsForValue().get(userinfo.getCaptcahKey()).equals(code)){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        // 对传入的数据进行处理
        if(userinfo.getPhone().length() != 11){
            throw new MFMException(201,"手机号格式不正确");
        }
        if(userinfo.getPassword().length() < 8){
            throw new MFMException(201,"密码长度不能小于8位");
        }
        if(StringUtils.hasText(userinfo.getNickname()) == false){
            userinfo.setNickname("用户:"+userinfo.getPhone());
        }
        if(StringUtils.hasText(userinfo.getAvaterUrl()) == false){
            userinfo.setAvaterUrl("https://c-ssl.dtstatic.com/uploads/blog/202311/10/d3S2eMNdFqmPpQp.thumb.1000_0.png");
        }
        if(userinfo.getCity() == null || userinfo.getProvince() == null){
            throw new MFMException(201,"未选择地址信息");
        }
        userinfo.setPassword(DigestUtils.md5Hex(userinfo.getPassword()));
        UserInfo userinfo1 = new UserInfo();
        userinfo1.setPhone(userinfo.getPhone());
        userinfo1.setPassword(userinfo.getPassword());
        userinfo1.setAvatarUrl(userinfo.getAvaterUrl());
        userinfo1.setNickname(userinfo.getNickname());
        LambdaUpdateWrapper<CityInfo> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(CityInfo::getCityName,userinfo.getCity());
        userinfo1.setProvinceId(cityInfoMapper.selectOne(queryWrapper).getProvinceId());
        userinfo1.setCityId(cityInfoMapper.selectOne(queryWrapper).getId());
        userinfo1.setRole("用户");
        userInfoMapper.insert(userinfo1);
    }

    @Override
    public UserInfoVo getUserInfo() {

        return userInfoMapper.getUserInfo(LoginHolder.getLoginUser().getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateInfo(UserInfo userinfo) {
        LambdaUpdateWrapper<UserInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserInfo::getId,userinfo.getId());
        userInfoMapper.update(userinfo,updateWrapper);
    }
}
