package com.Aaron.MFM.web.admin.mapper;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 食物表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface FoodInfoMapper extends BaseMapper<FoodInfo> {

    FoodInfoVo getFoodListById(Integer foodId);

    List<FoodInfoVo> getFoodList();
}
