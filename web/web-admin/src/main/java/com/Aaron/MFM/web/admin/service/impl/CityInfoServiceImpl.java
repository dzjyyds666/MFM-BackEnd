package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.web.admin.mapper.CityInfoMapper;
import com.Aaron.MFM.web.admin.mapper.CityInfoMapper;
import com.Aaron.MFM.web.admin.service.ICityInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 城市表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo> implements ICityInfoService {

    @Autowired
    private CityInfoMapper cityInfoMapper;

    @Override
    public List<CityInfo> getCityList(Integer provinceId) {
        if(provinceId != null){
            LambdaQueryWrapper<CityInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(CityInfo::getProvinceId,provinceId);
            return cityInfoMapper.selectList(queryWrapper);
        }else return cityInfoMapper.selectList(null);
    }
}
