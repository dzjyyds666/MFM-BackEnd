package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 套餐食物关系表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("combo_food_relation")
public class ComboFoodRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐食物关系id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 套餐id
     */
    private Integer comboId;

    /**
     * 食物id
     */
    private Integer foodId;

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

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "ComboFoodRelation{" +
            "id = " + id +
            ", comboId = " + comboId +
            ", foodId = " + foodId +
            ", isDelete = " + isDelete +
        "}";
    }
}
