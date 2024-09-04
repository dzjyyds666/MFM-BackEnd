package com.Aaron.MFM.web.admin.vo.combo;

import com.Aaron.MFM.model.entity.ComboInfo;
import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import lombok.Data;

import java.util.List;

@Data
public class ComboInfoVo extends ComboInfo {

    private List<FoodInfo> foodInfoList;

}
