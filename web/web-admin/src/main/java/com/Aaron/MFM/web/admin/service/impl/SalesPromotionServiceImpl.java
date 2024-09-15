package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.model.entity.SalesPromotion;
import com.Aaron.MFM.web.admin.mapper.SalesPromotionMapper;
import com.Aaron.MFM.web.admin.service.ISalesPromotionService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 促销表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class SalesPromotionServiceImpl extends ServiceImpl<SalesPromotionMapper, SalesPromotion> implements ISalesPromotionService {

    @Autowired
    private SalesPromotionMapper salesPromotionMapper;

    @Autowired
    @Qualifier("redisSToITemplate")
    private RedisTemplate<String,Integer> redisTemplate;
    @Override
    public List<SalesPromotion> getSalesPromotionList() {
        return salesPromotionMapper.getSalesPromotionList();
    }

    @Override
    public void upShelves(Integer id,Integer isShelves) {

        // 更新状态
        LambdaUpdateWrapper<SalesPromotion> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SalesPromotion::getId,id);
        wrapper.set(SalesPromotion::getIsShelves,isShelves);
        salesPromotionMapper.update(null,wrapper);

        SalesPromotion salesPromotion = salesPromotionMapper.selectById(id);

        if(isShelves == 1){
            // 添加缓存
            redisTemplate.opsForValue().set("salesPromotion:"+id,salesPromotion.getNumber());
        }else if(isShelves == 0){
            // 删除缓存
            redisTemplate.delete("salesPromotion:"+id);
        }

    }
}
