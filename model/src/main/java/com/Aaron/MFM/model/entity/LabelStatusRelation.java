package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 标签状态关系表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("label_status_relation")
public class LabelStatusRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签id
     */
    private Integer labelId;

    /**
     * 状态id
     */
    private Integer statusId;

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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "LabelStatusRelation{" +
            "id = " + id +
            ", labelId = " + labelId +
            ", statusId = " + statusId +
            ", isDelete = " + isDelete +
        "}";
    }
}
