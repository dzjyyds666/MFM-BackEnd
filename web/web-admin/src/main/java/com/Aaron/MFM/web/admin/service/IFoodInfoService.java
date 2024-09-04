package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.admin.vo.food.AddFoodInfoVo;
import com.Aaron.MFM.web.admin.vo.food.ChangeFoodInfoVo;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 食物表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface IFoodInfoService extends IService<FoodInfo> {

    List<FoodInfoVo> getFoodList();

    void changeFoodInfo(ChangeFoodInfoVo changeFoodInfoVo);

    void addFoodInfo(AddFoodInfoVo foodInfoVo);

    void removeFoodInfo(Long id);
}
