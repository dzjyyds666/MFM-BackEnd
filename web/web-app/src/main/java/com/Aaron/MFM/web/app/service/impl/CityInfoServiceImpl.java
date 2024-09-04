package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.CityInfo;
import com.Aaron.MFM.web.admin.service.ICityInfoService;
import com.Aaron.MFM.web.app.mapper.CityInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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


}
