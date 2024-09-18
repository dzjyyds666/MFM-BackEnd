package com.Aaron.MFM.web.app.controller.combo;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.aop.LimitAccess;
import com.Aaron.MFM.web.app.service.IComboInfoService;
import com.Aaron.MFM.web.app.vo.combo.ComboInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 套餐表 前端控制器
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@RestController
@RequestMapping("/app/combo")
@Tag(name = "app套餐管理")
public class ComboInfoController {

    @Autowired
    private IComboInfoService comboInfoService;

    @GetMapping("/getComboList")
    @Operation(summary = "获取套餐列表")
    @LimitAccess
    public Result<List<ComboInfoVo>> getComboList() {
        List<ComboInfoVo> comboInfoVoList = comboInfoService.getComboList();
        return Result.ok(comboInfoVoList);
    }



}
