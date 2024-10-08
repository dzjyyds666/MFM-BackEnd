package com.Aaron.MFM.web.app.controller.login;


import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.service.IUserInfoService;
import com.Aaron.MFM.web.app.vo.login.CaptchaVo;
import com.Aaron.MFM.web.app.vo.login.LoginInfoVo;
import com.Aaron.MFM.web.app.vo.user.createUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/login")
@Tag(name = "app登录管理")
public class LoginController {

    @Autowired
    private IUserInfoService userInfoService;


    @GetMapping("/getcaptcha")
    @Operation(summary = "获取验证码")
    public Result<CaptchaVo> getCaptcha(@RequestParam String type){

        CaptchaVo captchaVo = userInfoService.getCaptcha(type);
        return Result.ok(captchaVo);
    }

    @PostMapping
    @Operation(summary = "登录")
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

    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result<String> register(@RequestBody createUserVo userinfo){
        userInfoService.register(userinfo);
        return Result.ok("注册成功");
    }


}
