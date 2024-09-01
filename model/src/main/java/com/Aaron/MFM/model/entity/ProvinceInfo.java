package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 省份表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("province_info")
public class ProvinceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省份id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

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

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "ProvinceInfo{" +
            "id = " + id +
            ", provinceName = " + provinceName +
            ", createTime = " + createTime +
            ", isDelete = " + isDelete +
        "}";
    }
}
