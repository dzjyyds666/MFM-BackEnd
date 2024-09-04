package com.Aaron.MFM.web.admin.controller.user;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.web.admin.service.ICityInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/admin/user")
@Tag(name = "后台城市管理")
public class CityInfoController {

    @Autowired
    private ICityInfoService cityInfoService;

    @GetMapping("/getCityList")
    @Operation(summary = "根据省份获取城市列表")
    public Result<List<CityInfo>> getCityList(@RequestParam(required = false) Integer provinceId) {
        return Result.ok(cityInfoService.getCityList(provinceId));
    }
}
