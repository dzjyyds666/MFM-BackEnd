package com.Aaron.MFM.web.app.controller.user;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.service.IUserInfoService;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/user")
@Tag(name = "app用户信息管理")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户个人信息")
    @LimitAccess
    public Result<UserInfoVo> getUserInfo() {
        UserInfoVo userinfo = userInfoService.getUserInfo();
        return Result.ok(userinfo);
    }

    @PostMapping("/updateInfo")
    @Operation(summary = "更新用户个人信息")
    @LimitAccess
    public Result<String> updateInfo(@RequestBody UserInfo userinfo) {
        userInfoService.updateInfo(userinfo);
        return Result.ok("更新成功");
    }


}
