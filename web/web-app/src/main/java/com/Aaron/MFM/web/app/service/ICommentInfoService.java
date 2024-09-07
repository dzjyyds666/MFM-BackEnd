package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.web.app.vo.comment.CommentInfovo;
import com.Aaron.MFM.web.app.vo.comment.CommentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface ICommentInfoService extends IService<CommentInfo> {

    List<CommentVo> getCommentByFoodId(Integer foodId,Long userId);

    void addComment(CommentInfovo commentInfo);

}
