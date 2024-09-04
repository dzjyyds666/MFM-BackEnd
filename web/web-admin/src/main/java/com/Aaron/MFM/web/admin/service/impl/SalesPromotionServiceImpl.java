package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.model.entity.SalesPromotion;
import com.Aaron.MFM.web.admin.mapper.SalesPromotionMapper;
import com.Aaron.MFM.web.admin.service.ISalesPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 促销表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class SalesPromotionServiceImpl extends ServiceImpl<SalesPromotionMapper, SalesPromotion> implements ISalesPromotionService {

    @Autowired
    private SalesPromotionMapper salesPromotionMapper;
    @Override
    public List<SalesPromotion> getSalesPromotionList() {
        return salesPromotionMapper.getSalesPromotionList();
    }
}
