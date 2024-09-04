package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.result.ResultCodeEnum;
import com.Aaron.MFM.model.entity.FoodStatus;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.mapper.FoodStatusMapper;
import com.Aaron.MFM.web.admin.service.IFoodStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
