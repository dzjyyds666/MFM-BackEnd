package com.Aaron.MFM.web.app.custom.interceptor;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.contanst.RedisConstant;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.common.utils.JWTutils;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.mapper.UserInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        if(token == null){
            throw new MFMException(ResultCodeEnum.TOKRN_EMPTY);
        }
        Claims claims = JWTutils.parseToken(token);
        //TODO 去redis中校验token是否存在
        String redisToken = redisTemplate.opsForValue().get(RedisConstant.ADMIN_LOGIN_PREFIX + claims.get("userId"));
        if( redisToken== null || !redisToken.equals(token)){
            throw new MFMException(ResultCodeEnum.TOKEN_EXPIRED);
        }

        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getId,claims.get("userId"));
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);

        if(userInfo == null || userInfo.getStatus() == 1){
            throw new MFMException(ResultCodeEnum.USER_STATUS_ERROR);
        }
        LoginHolder.setLoginUser(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginHolder.remove();
    }
}
