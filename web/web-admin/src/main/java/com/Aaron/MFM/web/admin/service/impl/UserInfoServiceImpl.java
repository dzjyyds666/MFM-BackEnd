package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.contanst.RedisConstant;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.common.utils.JWTutils;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.mapper.UserInfoMapper;
import com.Aaron.MFM.web.admin.service.IUserInfoService;
import com.Aaron.MFM.web.admin.vo.login.CaptchaVo;
import com.Aaron.MFM.web.admin.vo.login.LoginInfoVo;
import com.Aaron.MFM.web.admin.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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


    /*
    * 获取登录图片验证码
    * */
    @Override
    public CaptchaVo getCaptcha() {
        // 定义验证码对象
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        // 设置类型
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 获取验证码 全部是小写
        String code = specCaptcha.text().toLowerCase();
        String image = specCaptcha.toBase64();
        // 生成key
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        redisTemplate.opsForValue().set(key,code,RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new CaptchaVo(image,key);
    }


    /*
    * 登录
    * */
    @Override
    public String login(LoginInfoVo loginInfoVo) {
        // 判断是否输入了验证码
        if(StringUtils.hasText(loginInfoVo.getCaptchaCode()) == false){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }

        // 判断验证码是否过期
        String code = redisTemplate.opsForValue().get(loginInfoVo.getCaptchaKey());
        if(code == null){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        // 判断验证码是否正确
        if(!code.equals(loginInfoVo.getCaptchaCode())){
            throw new MFMException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        // 获取用户信息
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone,loginInfoVo.getPhone());
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        // 判断用户是否存在
        if(userInfo == null){
            throw new MFMException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        // 判断用户是否被禁
        if(userInfo.getStatus() == 1){
            throw new MFMException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        // 校验用户密码
        if(!userInfo.getPassword().equals(DigestUtils.md5Hex(loginInfoVo.getPassword()))){
            throw new MFMException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
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
    public UserInfoVo getUserInfo() {
        UserInfo loginUser = LoginHolder.getLoginUser();
        UserInfoVo userInfo = userInfoMapper.getUserInfo(loginUser.getId());
        System.out.println("userInfo = " + userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfoVo> getUserList() {
        UserInfo loginUser = LoginHolder.getLoginUser();
        if(loginUser.getRole().equals("超级管理员") || loginUser.getRole().equals("管理员")){
            List<UserInfoVo> list = userInfoMapper.getUserList();
            return list;
        }else{
            throw new MFMException(ResultCodeEnum.USER_NOT_PERMISSION);
        }
    }

    @Override
    public void removeUser(Long id) {
        UserInfo loginUser = LoginHolder.getLoginUser();
        // 只有超级管理员可以删除用户
        if(loginUser.getRole().equals("超级管理员")){
            // 判断用户是否为超级管理员
            UserInfo userInfo = userInfoMapper.selectById(id);
            if(userInfo.getRole().equals("超级管理员")){
                throw new MFMException(201,"超级管理员不能删除");
            }else{
                userInfoMapper.deleteById(id);
            }
        }else {
            throw new MFMException(ResultCodeEnum.USER_NOT_PERMISSION);
        }
    }


}
