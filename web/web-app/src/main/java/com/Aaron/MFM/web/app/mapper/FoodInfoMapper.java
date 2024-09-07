package com.Aaron.MFM.web.app.mapper;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.app.vo.food.FoodInfoVo;
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

    List<FoodInfoVo> getFoodInfoByKey(String foodKey);

    FoodInfoVo getFoodInfoById(String foodId);
}
