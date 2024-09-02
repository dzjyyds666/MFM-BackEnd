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
 * 食物表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("food_info")
public class FoodInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 食物id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 食物名称
     */
    private String foodName;

    /**
     * 食物图片url
     */
    private String picUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Byte isDelete;

    /**
     * 食物价格
     */
    private BigDecimal price;

    /**
     * 食物类型id
     */
    private Integer TypeId;

    /**
     * 是否推荐
     */
    private Byte isRecommend;

    /**
     * 状态
     */
    private Integer statusId;

    /*
    * 描述信息
    * */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer foodTypeId) {
        this.TypeId = foodTypeId;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "FoodInfo{" +
            "id = " + id +
            ", foodName = " + foodName +
            ", picUrl = " + picUrl +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDelete = " + isDelete +
            ", price = " + price +
            ", TypeId = " + TypeId +
            ", isRecommend = " + isRecommend +
            ", statusId = " + statusId +
            ", description = " + description +
        "}";
    }
}
