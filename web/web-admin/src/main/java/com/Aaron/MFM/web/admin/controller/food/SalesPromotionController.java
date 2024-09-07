package com.Aaron.MFM.web.admin.controller.food;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.SalesPromotion;
import com.Aaron.MFM.web.admin.service.ISalesPromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 促销表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/admin/salesPromotion")
@Tag(name = "后台促销管理")
public class SalesPromotionController {

    @Autowired
    private ISalesPromotionService salesPromotionService;

    @PostMapping("/addOrUpdate")
    @Operation(summary = "添加或修改促销")
    public Result<String> addOrUpdateSalesPromotion(@RequestBody SalesPromotion salesPromotion){
        salesPromotionService.saveOrUpdate(salesPromotion);
        return  Result.ok();
    }

    @GetMapping("/Shelves")
    @Operation(summary = "上架或下架促销")
    public Result<String> shelvesSalesPromotion( @RequestParam Integer id,@RequestParam Integer isShelves){
        salesPromotionService.upShelves(id,isShelves);
        if(isShelves == 1){
            return Result.ok("上架成功");
        }else return Result.ok("下架成功");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除促销")
    public Result<String> deleteSalesPromotion(@RequestParam Integer id){
        salesPromotionService.removeById(id);
        return Result.ok();
    }

    @GetMapping("/getSalesPromotionList")
    @Operation(summary = "获取促销列表")
    public Result<List<SalesPromotion>> getSalesPromotionList(){
        List<SalesPromotion> list = salesPromotionService.getSalesPromotionList();
        return Result.ok(list);
    }

}
