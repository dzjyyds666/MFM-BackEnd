package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 食物标签关系表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("food_label_relation")
public class FoodLabelRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 食物id
     */
    private Integer foodId;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 是否删除
     */
    @TableLogic
    private Byte isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "FoodLabelRelation{" +
            "id = " + id +
            ", foodId = " + foodId +
            ", labelId = " + labelId +
            ", isDelete = " + isDelete +
        "}";
    }
}
