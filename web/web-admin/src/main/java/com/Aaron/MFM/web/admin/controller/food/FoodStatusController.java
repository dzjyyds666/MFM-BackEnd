package com.Aaron.MFM.web.admin.controller.food;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.model.entity.FoodStatus;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.service.IFoodStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 食物状态表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/admin/food")
@Tag(name = "后台菜品状态管理")
public class FoodStatusController {

    @Autowired
    private IFoodStatusService foodStatusService;


    @GetMapping("/getFoodStatusList")
    @Operation(summary = "获取食物状态列表")
    public Result<List<FoodStatus>> getFoodStatusList() {
        List<FoodStatus> list = foodStatusService.list();
        return Result.ok(list);
    }

    @PostMapping("addOrUpStatus")
    @Operation(summary = "添加或修改食物状态")
    public Result<String> addOrUpDateFoodStatus(@RequestBody FoodStatus foodStatus) {

        if(StringUtils.hasText(foodStatus.getStatusName()) == false){
            throw new MFMException(201,"状态名称不能为空");
        }
        foodStatusService.saveOrUpdate(foodStatus);
        return Result.ok();
    }

    @Operation(summary = "删除食物状态")
    @DeleteMapping("deleteFoodStatus")
    public Result<String> deleteFoodStatus(@RequestParam Integer id) {
        UserInfo loginUser = LoginHolder.getLoginUser();
        if (!loginUser.getRole().equals("超级管理员") && !loginUser.getRole().equals("管理员")) {
            throw new MFMException(ResultCodeEnum.USER_NOT_PERMISSION);
        }
        foodStatusService.removeById(id);
        return Result.ok();
    }
}
