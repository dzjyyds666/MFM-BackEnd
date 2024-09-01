package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 促销表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("sales_promotion")
public class SalesPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 促销id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 促销名称
     */
    private String promotionName;

    /**
     * 创建时间
     */
    private LocalDateTime beginTime;

    /**
     * 更新时间
     */
    private LocalDateTime endTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Byte isDelete;

    /**
     * 食物id
     */
    private Integer foodId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 价格
     */
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SalesPromotion{" +
            "id = " + id +
            ", promotionName = " + promotionName +
            ", beginTime = " + beginTime +
            ", endTime = " + endTime +
            ", isDelete = " + isDelete +
            ", foodId = " + foodId +
            ", number = " + number +
            ", price = " + price +
        "}";
    }
}
