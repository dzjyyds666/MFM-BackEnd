package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.web.admin.vo.combo.AddOrUpdateComboVo;
import com.Aaron.MFM.web.admin.vo.combo.ComboInfoVo;
import com.Aaron.MFM.web.admin.vo.combo.SearchComboVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 套餐表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface IComboInfoService extends IService<ComboInfo> {

    List<ComboInfoVo> getComboList();

    void addOrUpdateCombo(AddOrUpdateComboVo comboInfoVo);

    List<ComboInfoVo> searchByCondition(SearchComboVo searchComboVo);
}
