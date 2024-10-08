package com.Aaron.MFM.web.app.mapper;

import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.web.app.vo.combo.ComboInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 套餐表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface ComboInfoMapper extends BaseMapper<ComboInfo> {

    List<ComboInfoVo> getComboList();
}
