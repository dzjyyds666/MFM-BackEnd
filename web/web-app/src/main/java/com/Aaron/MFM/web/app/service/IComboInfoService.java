package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.web.app.vo.combo.ComboInfoVo;
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
}
