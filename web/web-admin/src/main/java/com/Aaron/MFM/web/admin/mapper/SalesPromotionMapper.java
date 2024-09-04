package com.Aaron.MFM.web.admin.mapper;

import com.Aaron.MFM.model.entity.SalesPromotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 促销表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface SalesPromotionMapper extends BaseMapper<SalesPromotion> {

    List<SalesPromotion> getSalesPromotionList();
}
