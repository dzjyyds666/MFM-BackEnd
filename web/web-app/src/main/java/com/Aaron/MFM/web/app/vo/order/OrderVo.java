package com.Aaron.MFM.web.app.vo.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderVo {

    private Integer id;

    private String orderNumber;

    private Integer stausId;

    private BigDecimal total;

    private Long userId;

    private List<Integer> foodIdList;
}
