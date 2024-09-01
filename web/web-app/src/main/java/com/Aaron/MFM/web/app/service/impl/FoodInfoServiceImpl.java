package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.app.mapper.FoodInfoMapper;
import com.Aaron.MFM.web.admin.service.IFoodInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食物表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class FoodInfoServiceImpl extends ServiceImpl<FoodInfoMapper, FoodInfo> implements IFoodInfoService {

}
