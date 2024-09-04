package com.Aaron.MFM.web.admin.vo.salePromition;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.SalesPromotion;
import lombok.Data;

@Data
public class SalePromitionvo extends SalesPromotion {
    private FoodInfo foodInfo;
}
