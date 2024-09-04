package com.Aaron.MFM.web.admin.vo.food;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AddFoodInfoVo {
    private String foodName;
    private String description;
    private String foodType;
    private Integer statusId;
    private String picUrl;
    private BigDecimal price;
    private List<Integer> labelIdList;
    private String foodKey;
}
