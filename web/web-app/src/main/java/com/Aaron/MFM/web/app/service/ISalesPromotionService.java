package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.model.entity.SalesPromotion;
import com.Aaron.MFM.web.app.vo.order.SalesPromotionVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 促销表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface ISalesPromotionService extends IService<SalesPromotion> {

    List<SalesPromotionVo> getSalePromotionList();

    String snapped(Integer id);
}
