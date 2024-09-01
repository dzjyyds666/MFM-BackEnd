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
 * 订单表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 下单时间
     */
    private LocalDateTime createTime;

    /**
     * 订单状态id
     */
    private Integer stausId;

    /**
     * 是否删除
     */
    @TableLogic
    private Byte isDelete;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总价格
     */
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getStausId() {
        return stausId;
    }

    public void setStausId(Integer stausId) {
        this.stausId = stausId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
            "id = " + id +
            ", orderNumber = " + orderNumber +
            ", createTime = " + createTime +
            ", stausId = " + stausId +
            ", isDelete = " + isDelete +
            ", userId = " + userId +
            ", total = " + total +
        "}";
    }
}
