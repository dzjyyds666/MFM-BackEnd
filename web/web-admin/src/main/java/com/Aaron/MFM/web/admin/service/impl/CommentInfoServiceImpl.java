package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.web.admin.mapper.CommentInfoMapper;
import com.Aaron.MFM.web.admin.service.ICommentInfoService;
import com.Aaron.MFM.web.admin.vo.comment.CommentInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class CommentInfoServiceImpl extends ServiceImpl<CommentInfoMapper, CommentInfo> implements ICommentInfoService {

    @Autowired
    private CommentInfoMapper commentInfoMapper;
    @Override
    public List<CommentInfoVo> getCommentList() {
        return commentInfoMapper.getCommentList();
    }

    @Override
    public List<CommentInfoVo> getCommentListByUserId(Long userId) {
        return commentInfoMapper.getCommentListByUserId(userId);
    }

    @Override
    public List<CommentInfoVo> getCommentListByFoodId(Integer foodId) {
        return commentInfoMapper.getCommentListByFoodId(foodId);
    }
}
