package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.mapper.UserInfoMapper;
import com.Aaron.MFM.web.admin.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
