package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 人员角色
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserRole() {
    }

    public UserRole(String id, String userCode, String roleCode, LocalDateTime attachDatetime) {
        this.id = id;
        this.userCode = userCode;
        this.roleCode = roleCode;
        this.attachDatetime = attachDatetime;
    }

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 人员编码
     */
    private String userCode;

    /**
     * 角色编码
     */
    private String roleCode;

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
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public LocalDateTime getAttachDatetime() {
        return attachDatetime;
    }

    public void setAttachDatetime(LocalDateTime attachDatetime) {
        this.attachDatetime = attachDatetime;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", roleCode=" + roleCode +
        ", attachDatetime=" + attachDatetime +
        "}";
    }
}
