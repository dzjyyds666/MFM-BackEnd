package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.FoodType;
import com.Aaron.MFM.web.app.mapper.FoodTypeMapper;
import com.Aaron.MFM.web.admin.service.IFoodTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食物类型表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType> implements IFoodTypeService {

}
