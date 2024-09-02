package com.Aaron.MFM.web.admin.vo.food;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ChangeFoodInfoVo {

    private Integer id;
    private String foodName;
    private String description;
    private String picUrl;
    private BigDecimal price;
    private List<Integer> foodLabelList;
}
