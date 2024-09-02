package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.FoodLabelRelation;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.mapper.FoodInfoMapper;
import com.Aaron.MFM.web.admin.mapper.FoodLabelRelationMapper;
import com.Aaron.MFM.web.admin.service.IFoodInfoService;
import com.Aaron.MFM.web.admin.vo.food.ChangeFoodInfoVo;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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

    @Autowired
    private FoodLabelRelationMapper foodLabelRelationMapper;

    @Override
    public List<FoodInfoVo> getFoodList() {
        List<FoodInfoVo> list = foodInfoMapper.getFoodList();
        return list;
    }

    @Override
    public void changeFoodInfo(ChangeFoodInfoVo changefoodInfoVo) {
        FoodInfo foodInfo = new FoodInfo();
        foodInfo.setId(changefoodInfoVo.getId());
        if(StringUtils.hasText(changefoodInfoVo.getFoodName()) == false){
            throw new MFMException(201, "修改食物信息失败，名称不能为空");
        }
        foodInfo.setFoodName(changefoodInfoVo.getFoodName());
        if(changefoodInfoVo.getPrice() == null || changefoodInfoVo.getPrice().compareTo(BigDecimal.ZERO) == 0){
            throw new MFMException(201, "修改食物信息失败，价格不能为空");
        }
        foodInfo.setPrice(changefoodInfoVo.getPrice());
        foodInfo.setDescription(changefoodInfoVo.getDescription());
        if(StringUtils.hasText(changefoodInfoVo.getPicUrl())== false){
            throw new MFMException(201, "修改食物信息失败，图片不能为空");
        }
        foodInfo.setPicUrl(changefoodInfoVo.getPicUrl());
        foodInfoMapper.updateById(foodInfo);

        // 修改标签 删除原本标签,添加新标签
        LambdaQueryWrapper<FoodLabelRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FoodLabelRelation::getFoodId, changefoodInfoVo.getId());
        foodLabelRelationMapper.delete(queryWrapper);

        for (Integer id : changefoodInfoVo.getFoodLabelList()) {
            FoodLabelRelation foodLabelRelation = new FoodLabelRelation();
            foodLabelRelation.setFoodId(changefoodInfoVo.getId());
            foodLabelRelation.setLabelId(id);
            foodLabelRelationMapper.insert(foodLabelRelation);
        }
    }

    @Override
    public void addFoodInfo(FoodInfoVo foodInfoVo) {
        UserInfo loginUser = LoginHolder.getLoginUser();
        if( !loginUser.getRole().equals("超级管理员") && !loginUser.getRole().equals("管理员")){
            throw new MFMException(ResultCodeEnum.USER_NOT_PERMISSION);
        }

        if(StringUtils.hasText(foodInfoVo.getFoodName())){

        }
    }
}
