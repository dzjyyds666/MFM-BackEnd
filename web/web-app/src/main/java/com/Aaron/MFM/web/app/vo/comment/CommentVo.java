package com.Aaron.MFM.web.app.vo.comment;

import com.Aaron.MFM.model.entity.CommentInfo;
import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.UserInfo;
import lombok.Data;

@Data
public class CommentVo extends CommentInfo {

    private UserInfo userInfo;

    private FoodInfo foodInfo;
}
