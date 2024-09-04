package com.Aaron.MFM.web.admin.controller.food;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.admin.service.IFoodInfoService;
import com.Aaron.MFM.web.admin.vo.food.AddFoodInfoVo;
import com.Aaron.MFM.web.admin.vo.food.ChangeFoodInfoVo;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/food")
@Tag(name = "后台菜品管理")
public class FoodInfoController {

    @Autowired
    private IFoodInfoService foodInfoService;

    @Operation(summary = "获取菜品列表")
    @GetMapping("/getFoodList")
    public Result<List<FoodInfoVo>> getFoodList(){
        List<FoodInfoVo> list = foodInfoService.getFoodList();
        return Result.ok(list);
    }

    @Operation(summary = "修改菜品信息")
    @PostMapping("/changeFoodInfo")
    public Result<FoodInfoVo> getFoodDetail(@RequestBody ChangeFoodInfoVo changeFoodInfoVo){
        foodInfoService.changeFoodInfo(changeFoodInfoVo);
        return Result.ok();
    }

    @Operation(summary = "添加菜品信息")
    @PostMapping("/addFoodInfo")
    public Result<String> addFoodInfo(@RequestBody AddFoodInfoVo foodInfoVo){
        foodInfoService.addFoodInfo(foodInfoVo);
        return Result.ok();
    }

    @DeleteMapping("deleteFoodInfo")
    @Operation(summary = "删除菜品信息")
    public Result<String> deleteFoodInfo(@RequestParam Long id){
        foodInfoService.removeFoodInfo(id);
        return Result.ok("删除成功");
    }

}
