package com.Aaron.MFM.web.app.aop;


import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.model.entity.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class LimitAccessAspect {

    @Autowired
    @Qualifier("redisSToITemplate")
    private RedisTemplate<String,Integer> redisTemplate;

    @Around("@annotation(limitAccess)")
    public Object limitAccess(ProceedingJoinPoint joinPoint,LimitAccess limitAccess) throws Throwable {
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String requestURI = null;
        if (request != null) {
            requestURI = request.getRequest().getRequestURI();
        }
        UserInfo userInfo = LoginHolder.getLoginUser();
        String redisKey = "user-" + userInfo.getId() + "-" + requestURI;
        Integer count = redisTemplate.opsForValue().get(redisKey);
        if (count == null){
            // 第一次访问，设置过期时间
            redisTemplate.opsForValue().set(redisKey,1,60, TimeUnit.SECONDS);
        }else if(count >= limitAccess.maxAccessCount()){
            throw new MFMException(ResultCodeEnum.REPEAT_SUBMIT);
        }else{
            redisTemplate.opsForValue().increment(redisKey);
        }

        return joinPoint.proceed();
    }
}
