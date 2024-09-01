package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.PermissionInfo;
import com.Aaron.MFM.web.app.mapper.PermissionInfoMapper;
import com.Aaron.MFM.web.admin.service.IPermissionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class PermissionInfoServiceImpl extends ServiceImpl<PermissionInfoMapper, PermissionInfo> implements IPermissionInfoService {

}
