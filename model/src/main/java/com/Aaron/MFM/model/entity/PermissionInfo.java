package com.Aaron.MFM.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@TableName("permission_info")
public class PermissionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String permissionName;

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

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "PermissionInfo{" +
            "id = " + id +
            ", permissionName = " + permissionName +
            ", isDelete = " + isDelete +
        "}";
    }
}
