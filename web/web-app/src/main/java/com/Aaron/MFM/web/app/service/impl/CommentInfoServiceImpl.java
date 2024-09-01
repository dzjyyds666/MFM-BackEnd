package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.web.app.mapper.CommentInfoMapper;
import com.Aaron.MFM.web.admin.service.ICommentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
