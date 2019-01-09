package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_role_function")
public class RoleFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    public RoleFunction() {
    }

    public RoleFunction(String id, String roleCode, String functionCode, LocalDateTime attachDatetime) {
        this.id = id;
        this.roleCode = roleCode;
        this.functionCode = functionCode;
        this.attachDatetime = attachDatetime;
    }

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 功能编码
     */
    private String functionCode;

    /**
     * 分配时间
     */
    private LocalDateTime attachDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }
    public LocalDateTime getAttachDatetime() {
        return attachDatetime;
    }

    public void setAttachDatetime(LocalDateTime attachDatetime) {
        this.attachDatetime = attachDatetime;
    }

    @Override
    public String toString() {
        return "RoleFunction{" +
        "id=" + id +
        ", roleCode=" + roleCode +
        ", functionCode=" + functionCode +
        ", attachDatetime=" + attachDatetime +
        "}";
    }
}
