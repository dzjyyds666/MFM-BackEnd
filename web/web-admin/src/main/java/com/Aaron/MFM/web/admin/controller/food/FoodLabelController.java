package com.Aaron.MFM.web.admin.controller.food;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.model.entity.FoodLabel;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.aop.LimitAccess;
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
@Tag(name = "后台菜品标签管理")
@RequestMapping("admin/food")
public class FoodLabelController {

    @Autowired
    private IFoodLabelService foodLabelService;

    @Operation(summary = "获取菜品标签列表")
    @GetMapping("/getFoodLabelList")
    @LimitAccess
    public Result<List<FoodLabel>> getFoodLabelList(){
        List<FoodLabel> list = foodLabelService.list();
        return Result.ok(list);
    }

    @Operation(summary = "添加或修改菜品标签")
    @PostMapping("/addOrUpdateFoodLabel")
    @LimitAccess
    public Result<String> addOrUpdateFoodLabel(@RequestBody FoodLabel foodLabel){

        if(StringUtils.hasText(foodLabel.getLabelName()) == false){
            throw  new MFMException(201,"标签名不能为空");
        }
        foodLabelService.saveOrUpdate(foodLabel);
        return Result.ok("操作成功");
    }

    @Operation(summary = "删除菜品标签")
    @DeleteMapping("/deleteFoodLabel")
    @LimitAccess
    public Result<String> deleteFoodLabel(@RequestParam Long id){
        foodLabelService.removeById(id);
        return Result.ok("操作成功");
    }

}
