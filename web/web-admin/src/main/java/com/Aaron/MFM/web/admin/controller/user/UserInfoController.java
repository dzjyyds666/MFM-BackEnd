package com.Aaron.MFM.web.admin.controller.user;

import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.service.IUserInfoService;
import com.Aaron.MFM.web.admin.vo.user.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
        return Result.ok(list);
    }

    @PostMapping("/upOradd")
    @Operation(summary = "注册或修改用户信息")
    public Result<String> register(@RequestBody UserInfo userInfo) {


        if(StringUtils.hasText(userInfo.getPhone()) == false){
            throw new MFMException(201,"手机号不能为空");
        }
        if(StringUtils.hasText(userInfo.getPassword()) == false){
            throw new MFMException(201,"密码不能为空");
        }
        if(StringUtils.hasText(userInfo.getNickname()) == false){
            userInfo.setNickname("用户"+userInfo.getPhone());
        }
        if(StringUtils.hasText(userInfo.getAvatarUrl()) == false){
            userInfo.setAvatarUrl("https://pic4.zhimg.com/80/v2-261c2b442c8b4dabdded10c828d3ebc5_1440w.webp");
        }
        userInfo.setPassword(DigestUtils.md5Hex(userInfo.getPassword()));
        userInfoService.saveOrUpdate(userInfo);
        return Result.ok("成功");
    }
}
