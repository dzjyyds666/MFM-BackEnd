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
 * 套餐表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("combo_info")
public class ComboInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 套餐名称
     */
    private String comboName;

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
     * 套餐价格
     */
    private BigDecimal price;

    /**
     * 是否推荐
     */
    private Byte isRecommend;

    /**
     * 是否下架
     */
    private Byte isTakeoff;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
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

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Byte getIsTakeoff() {
        return isTakeoff;
    }

    public void setIsTakeoff(Byte isTakeoff) {
        this.isTakeoff = isTakeoff;
    }


    @Override
    public String toString() {
        return "ComboInfo{" +
            "id = " + id +
            ", comboName = " + comboName +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", isDelete = " + isDelete +
            ", price = " + price +
            ", isRecommend = " + isRecommend +
            ", isTakeoff = " + isTakeoff +
        "}";
    }
}
