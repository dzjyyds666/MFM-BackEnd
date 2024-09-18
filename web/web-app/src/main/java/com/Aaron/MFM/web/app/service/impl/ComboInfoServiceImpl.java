package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.web.app.mapper.ComboInfoMapper;
import com.Aaron.MFM.web.app.service.IComboInfoService;
import com.Aaron.MFM.web.app.vo.combo.ComboInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public List<ComboInfoVo> getComboList() {

        return comboInfoMapper.getComboList();
    }
}
