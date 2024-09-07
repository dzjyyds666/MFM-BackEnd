package com.Aaron.MFM.web.app.vo.food;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.FoodLabel;
import com.Aaron.MFM.model.entity.FoodStatus;
import lombok.Data;

import java.util.List;

@Data
public class FoodInfoVo extends FoodInfo {

    private FoodStatus foodStatus;

    private List<FoodLabel> foodLabelList;
}
