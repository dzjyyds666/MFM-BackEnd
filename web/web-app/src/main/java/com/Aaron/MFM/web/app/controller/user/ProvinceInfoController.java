package com.Aaron.MFM.web.app.controller.user;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.model.entity.ProvinceInfo;
import com.Aaron.MFM.web.admin.service.IProvinceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 省份表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/user")
@Tag(name = "app用户地址管理")
public class ProvinceInfoController {

    @Autowired
    private IProvinceInfoService provinceInfoService;

    @GetMapping("/getProvinceList")
    @Operation(summary = "获取省份列表")
    public Result<List<ProvinceInfo>> getProvinceList() {
        return Result.ok(provinceInfoService.list());
    }
}
