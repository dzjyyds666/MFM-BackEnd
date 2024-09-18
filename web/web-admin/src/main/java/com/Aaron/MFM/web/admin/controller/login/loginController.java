package com.Aaron.MFM.web.admin.controller.login;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.admin.aop.LimitAccess;
import com.Aaron.MFM.web.admin.service.IUserInfoService;
import com.Aaron.MFM.web.admin.vo.login.CaptchaVo;
import com.Aaron.MFM.web.admin.vo.login.LoginInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/login")
@Tag(name = "后台管理系统登录管理")
public class loginController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/captcha")
    @Operation(summary = "获取验证码")
    @LimitAccess(maxAccessCount = 1)
    public Result<CaptchaVo> getCaptcha(){
        CaptchaVo captchaVo = userInfoService.getCaptcha();
        return Result.ok(captchaVo);
    }

    @PostMapping
    @Operation(summary = "登录")
    @LimitAccess
    public Result<String> login(@RequestBody LoginInfoVo loginInfoVo){
        String token = userInfoService.login(loginInfoVo);
        return Result.ok(token);
    }

    @GetMapping("/logout")
    @Operation(summary = "退出登录")
    public Result<String> logout(){
        userInfoService.logout();
        return Result.ok("退出成功");
    }
}
