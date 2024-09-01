package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.FoodLabel;
import com.Aaron.MFM.web.app.mapper.FoodLabelMapper;
import com.Aaron.MFM.web.admin.service.IFoodLabelService;
import com.Aaron.MFM.web.app.mapper.FoodLabelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食物标签表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class FoodLabelServiceImpl extends ServiceImpl<FoodLabelMapper, FoodLabel> implements IFoodLabelService {

}
