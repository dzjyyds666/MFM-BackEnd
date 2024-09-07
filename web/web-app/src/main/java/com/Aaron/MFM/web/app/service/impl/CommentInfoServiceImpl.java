package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.mapper.CommentInfoMapper;
import com.Aaron.MFM.web.app.service.ICommentInfoService;
import com.Aaron.MFM.web.app.vo.comment.CommentInfovo;
import com.Aaron.MFM.web.app.vo.comment.CommentVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CommentVo> getCommentByFoodId(Integer foodId,Long userId) {
        return commentInfoMapper.getCommentByFoodId(foodId,userId);
    }

    @Override
    public void addComment(CommentInfovo commentInfo) {
        UserInfo loginUser = LoginHolder.getLoginUser();

        CommentInfo comment = new CommentInfo();
        comment.setCommentContent(commentInfo.getCommentContent());
        comment.setFoodId(commentInfo.getFoodId());
        comment.setUserId(loginUser.getId());

        commentInfoMapper.insert(comment);
    }
}
