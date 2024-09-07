package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.app.mapper.FoodInfoMapper;
import com.Aaron.MFM.web.app.service.IFoodInfoService;
import com.Aaron.MFM.web.app.vo.food.FoodInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private FoodInfoMapper foodInfoMapper;

    @Override
    public List<FoodInfoVo> getFoodInfoByKey(String foodKey) {
        return foodInfoMapper.getFoodInfoByKey(foodKey);
    }

    @Override
    public FoodInfoVo getFoodInfoById(String foodId) {
        return foodInfoMapper.getFoodInfoById(foodId);
    }
}
