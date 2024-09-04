package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.model.entity.ComboFoodRelation;
import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.admin.mapper.ComboFoodRelationMapper;
import com.Aaron.MFM.web.admin.mapper.ComboInfoMapper;
import com.Aaron.MFM.web.admin.mapper.FoodInfoMapper;
import com.Aaron.MFM.web.admin.service.IComboInfoService;
import com.Aaron.MFM.web.admin.vo.combo.AddOrUpdateComboVo;
import com.Aaron.MFM.web.admin.vo.combo.ComboInfoVo;
import com.Aaron.MFM.web.admin.vo.combo.SearchComboVo;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 套餐表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class ComboInfoServiceImpl extends ServiceImpl<ComboInfoMapper, ComboInfo> implements IComboInfoService {

    @Autowired
    private ComboInfoMapper comboInfoMapper;

    @Autowired
    private FoodInfoMapper foodInfoMapper;

    @Autowired
    private ComboFoodRelationMapper comboFoodRelationMapper;
    @Override
    public List<ComboInfoVo> getComboList() {
        List<ComboInfo> comboInfoList = comboInfoMapper.selectList(null);
        List<ComboInfoVo> comboInfoVoList = new ArrayList<>();
        for(ComboInfo comboInfo:comboInfoList){
            ComboInfoVo comboInfoVo = new ComboInfoVo();
            //存入基础信息
            comboInfoVo.setId(comboInfo.getId());
            comboInfoVo.setComboName(comboInfo.getComboName());
            comboInfoVo.setPrice(comboInfo.getPrice());
            comboInfoVo.setCreateTime(comboInfo.getCreateTime());
            comboInfoVo.setUpdateTime(comboInfo.getUpdateTime());
            comboInfoVo.setIsRecommend(comboInfo.getIsRecommend());
            comboInfoVo.setIsTakeoff(comboInfo.getIsTakeoff());

            //获取套餐食物对应信息
            LambdaUpdateWrapper<ComboFoodRelation> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(ComboFoodRelation::getComboId,comboInfo.getId());
            List<ComboFoodRelation> comboFoodRelationList = comboFoodRelationMapper.selectList(wrapper);

            List<FoodInfo> foodIdList = new ArrayList<>();
            for(ComboFoodRelation comboFoodRelation:comboFoodRelationList){
                foodIdList.add(foodInfoMapper.getFoodListById(comboFoodRelation.getFoodId()));
            }
            comboInfoVo.setFoodInfoList(foodIdList);
            comboInfoVoList.add(comboInfoVo);
        }

        return comboInfoVoList;
    }

    @Override
    public void addOrUpdateCombo(AddOrUpdateComboVo comboInfoVo) {
        ComboInfo comboInfo = new ComboInfo();
        if(comboInfoVo.getId() == null){

            comboInfo.setComboName(comboInfoVo.getComboName());
            comboInfo.setPrice(comboInfoVo.getPrice());

            comboInfoMapper.insert(comboInfo);

            // 插入套餐和食物对应关系
            for (Integer i : comboInfoVo.getFoodIdList()) {
                ComboFoodRelation comboFoodRelation = new ComboFoodRelation();
                comboFoodRelation.setComboId(comboInfo.getId());
                comboFoodRelation.setFoodId(i);
                comboFoodRelationMapper.insert(comboFoodRelation);
            }
        }else {
            comboInfo.setId(comboInfoVo.getId());
            comboInfo.setComboName(comboInfoVo.getComboName());
            comboInfo.setPrice(comboInfoVo.getPrice());
            comboInfoMapper.updateById(comboInfo);

            // 删除之前的套餐食物对应关系
            LambdaQueryWrapper<ComboFoodRelation> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ComboFoodRelation::getComboId,comboInfoVo.getId());
            comboFoodRelationMapper.delete(wrapper);

            for (Integer i : comboInfoVo.getFoodIdList()) {
                ComboFoodRelation comboFoodRelation = new ComboFoodRelation();
                comboFoodRelation.setComboId(comboInfo.getId());
                comboFoodRelation.setFoodId(i);
                comboFoodRelationMapper.insert(comboFoodRelation);
            }
        }
    }

    @Override
    public List<ComboInfoVo> searchByCondition(SearchComboVo searchComboVo) {
        List<ComboInfoVo> comboList = getComboList();
        List<ComboInfoVo> resultList = new ArrayList<>();

        for (ComboInfoVo comboInfoVo : comboList) {
            if(StringUtils.hasText(searchComboVo.getComboName()) == true){
                if(!comboInfoVo.getComboName().contains(searchComboVo.getComboName())){
                    continue;
                }
            }
            if(searchComboVo.getIsRecommend() != null){
                if(!comboInfoVo.getIsRecommend().equals(searchComboVo.getIsRecommend())){
                    continue;
                }
            }
            if(searchComboVo.getIsTakeoff() != null){
                if(!comboInfoVo.getIsTakeoff().equals(searchComboVo.getIsTakeoff())){
                    continue;
                }
            }
            resultList.add(comboInfoVo);
        }

        return resultList;
    }
}
