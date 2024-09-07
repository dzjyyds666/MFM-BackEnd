package com.Aaron.MFM.web.app.vo.combo;

import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.model.entity.FoodInfo;
import lombok.Data;

import java.util.List;

@Data
public class ComboInfoVo extends ComboInfo {
    private List<FoodInfo> foodList;
}
