package com.Aaron.MFM.web.admin.vo.food;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.FoodLabel;
import com.Aaron.MFM.model.entity.FoodStatus;
import com.Aaron.MFM.model.entity.FoodType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "食品信息")
public class FoodInfoVo extends FoodInfo {

    @Schema(description = "食品种类")
    private FoodType foodType;

    @Schema(description = "食品状态")
    private FoodStatus foodStatus;

    @Schema(description = "食品标签")
    private List<FoodLabel> foodLabelList;
}
