package com.Aaron.MFM.web.admin.controller.combo;

import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.admin.service.IComboInfoService;
import com.Aaron.MFM.web.admin.vo.combo.AddOrUpdateComboVo;
import com.Aaron.MFM.web.admin.vo.combo.ComboInfoVo;
import com.Aaron.MFM.web.admin.vo.combo.SearchComboVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/combo")
@Tag(name = "后台套餐管理")
public class ComboInfoController {

    @Autowired
    private IComboInfoService comboInfoService;

    @GetMapping("/getCommentList")
    @Operation(summary = "获取套餐列表")
    public Result<List<ComboInfoVo>> getComboList() {
        return Result.ok(comboInfoService.getComboList());
    }

    @PostMapping("addOrUpdate")
    @Operation(summary = "添加或修改套餐")
    public Result<String> addOrUpdateCombo(@RequestBody AddOrUpdateComboVo comboInfoVo) {

        System.out.println(comboInfoVo);
        comboInfoService.addOrUpdateCombo(comboInfoVo);
        return Result.ok("成功");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除套餐")
    public Result<String> deleteCombo(@RequestParam Integer id) {
        comboInfoService.removeById(id);
        return Result.ok("删除成功");
    }

    @PostMapping("/searchByCondition")
    @Operation(summary = "根据条件查找套餐")
    public Result<List<ComboInfoVo>> searchByCondition(@RequestBody SearchComboVo searchComboVo) {
        List<ComboInfoVo> list = comboInfoService.searchByCondition(searchComboVo);
        return Result.ok(list);
    }
}
