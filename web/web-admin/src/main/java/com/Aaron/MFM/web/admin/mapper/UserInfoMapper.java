package com.Aaron.MFM.web.admin.mapper;

import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfoVo getUserInfo(Long id);

    List<UserInfoVo> getUserList();
}
