package com.Aaron.MFM.web.app.controller.food;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.service.IFoodInfoService;
import com.Aaron.MFM.web.app.vo.food.FoodInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 食物表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/food")
@Tag(name = "app食物管理")
public class FoodInfoController {

    @Autowired
    private IFoodInfoService foodInfoService;

    @GetMapping("/getFoodInfoByKey")
    @Operation(summary = "根据foodKey获取食物信息")
    @LimitAccess
    public Result<List<FoodInfoVo>> getFoodInfoByKey(@RequestParam(required = false) String foodKey) {
        List<FoodInfoVo> foodInfoList = foodInfoService.getFoodInfoByKey(foodKey);
        return Result.ok(foodInfoList);
    }

    @GetMapping("/getFoodInfoById")
    @Operation(summary = "根据foodId获取食物信息")
    @LimitAccess
    public Result<FoodInfoVo> getFoodInfoById(@RequestParam String foodId) {
        FoodInfoVo foodInfo = foodInfoService.getFoodInfoById(foodId);
        return Result.ok(foodInfo);
    }

}
