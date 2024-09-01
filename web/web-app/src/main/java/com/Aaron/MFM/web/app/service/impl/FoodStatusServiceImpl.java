package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.FoodStatus;
import com.Aaron.MFM.web.app.mapper.FoodStatusMapper;
import com.Aaron.MFM.web.admin.service.IFoodStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食物状态表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class FoodStatusServiceImpl extends ServiceImpl<FoodStatusMapper, FoodStatus> implements IFoodStatusService {

}
