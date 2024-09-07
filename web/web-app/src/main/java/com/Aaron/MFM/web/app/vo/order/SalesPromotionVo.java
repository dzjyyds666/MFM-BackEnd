package com.Aaron.MFM.web.app.vo.order;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.SalesPromotion;
import lombok.Data;

@Data
public class SalesPromotionVo extends SalesPromotion {

    private FoodInfo foodInfo;
}
