package com.Aaron.MFM.web.admin.controller.user;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.service.IUserInfoService;
import com.Aaron.MFM.web.admin.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/admin/user")
@Tag(name = "后台用户信息管理")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("/getuser")
    @Operation(summary = "获取用户个人信息")
    public Result<UserInfoVo> getUserInfo() {
        UserInfoVo userInfo = userInfoService.getUserInfo();
        return Result.ok(userInfo);
    }

    @GetMapping("/getuserlist")
    @Operation(summary = "获取用户列表")
    public Result<List<UserInfoVo>> getUserList() {
        List<UserInfoVo> list = userInfoService.getUserList();
        return Result.ok();
    }

}
