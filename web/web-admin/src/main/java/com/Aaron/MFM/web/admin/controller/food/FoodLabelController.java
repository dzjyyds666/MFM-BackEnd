package com.Aaron.MFM.web.admin.controller.food;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.model.entity.FoodLabel;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.service.IFoodLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 食物标签表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@Tag(name = "后台食物管理")
@RequestMapping("admin/food")
public class FoodLabelController {

    @Autowired
    private IFoodLabelService foodLabelService;

    @Operation(summary = "获取食物标签列表")
    @GetMapping("/getFoodLabelList")
    public Result<List<FoodLabel>> getFoodLabelList(){
        List<FoodLabel> list = foodLabelService.list();
        return Result.ok(list);
    }

    @Operation(summary = "添加或修改食物标签")
    @PostMapping("/addOrUpdateFoodLabel")
    public Result<String> addOrUpdateFoodLabel(@RequestBody FoodLabel foodLabel){

        // 判断是否为管理员和超级管理员
        if(foodLabel.getId() == null){
            UserInfo loginUser = LoginHolder.getLoginUser();
            if(!loginUser.getRole().equals("超级管理员") && !loginUser.getRole().equals("管理员")){
                throw new MFMException(ResultCodeEnum.USER_NOT_PERMISSION);
            }
        }
        if(StringUtils.hasText(foodLabel.getLabelName()) == false){
            throw  new MFMException(201,"标签名不能为空");
        }
        foodLabelService.saveOrUpdate(foodLabel);
        return Result.ok("操作成功");
    }

    @Operation(summary = "删除食物标签")
    @DeleteMapping("/deleteFoodLabel")
    public Result<String> deleteFoodLabel(@RequestParam Long id){
        UserInfo loginUser = LoginHolder.getLoginUser();
        if(!loginUser.getRole().equals("超级管理员") && !loginUser.getRole().equals("管理员")){
            throw new MFMException(ResultCodeEnum.USER_NOT_PERMISSION);
        }
        foodLabelService.removeById(id);
        return Result.ok("操作成功");
    }

}
