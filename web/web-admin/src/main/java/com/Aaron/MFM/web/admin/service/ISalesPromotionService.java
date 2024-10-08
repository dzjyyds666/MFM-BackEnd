package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.model.entity.SalesPromotion;
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

    List<SalesPromotion> getSalesPromotionList();

    void upShelves(Integer id,Integer isShelves);
}
