package com.Aaron.MFM.web.app.controller.user;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.web.admin.service.ICityInfoService;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kotlin.jvm.internal.Lambda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 城市表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/user")
@Tag(name = "app用户地址管理")
public class CityInfoController {

    @Autowired
    private ICityInfoService cityInfoService;

    @GetMapping("/getCityList")
    @Operation(summary = "获取城市列表")
    @LimitAccess
    public Result<List<CityInfo>> getCityList(Integer provinceId) {
        LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CityInfo::getProvinceId, provinceId);
        return Result.ok(cityInfoService.list(queryWrapper));
    }
}
