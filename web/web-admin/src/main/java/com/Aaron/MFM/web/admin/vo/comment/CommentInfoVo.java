package com.Aaron.MFM.web.admin.vo.comment;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import lombok.Data;

@Data
public class CommentInfoVo extends CommentInfo {

    private UserInfo userInfo;

    private FoodInfo foodInfo;

}
