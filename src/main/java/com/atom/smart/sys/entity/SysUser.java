package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 人员
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public SysUser() {
    }

    public SysUser(String id, String code, String name, String password, String orgCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.password = password;
        this.orgCode = orgCode;
    }

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否被锁
     */
    private Boolean isLock;

    /**
     * 是否有效
     */
    private Boolean isEnable;

    /**
     * 用户有效期
     */
    private Long userExpire;

    /**
     * 密码有效期
     */
    private Long pwdExpire;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 电话
     */
    private String telNo;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createItem;

    /**
     * 所属组织机构
     */
    private String orgCode;

    @TableField(exist = false)
    private List<SysRole> sysAllRoles;

    @TableField(exist = false)
    private List<SysFunction> sysAllFunctions;

    public List<SysRole> getSysAllRoles() {
        return sysAllRoles;
    }

    public void setSysAllRoles(List<SysRole> sysAllRoles) {
        this.sysAllRoles = sysAllRoles;
    }

    public List<SysFunction> getSysAllFunctions() {
        return sysAllFunctions;
    }

    public void setSysAllFunctions(List<SysFunction> sysAllFunctions) {
        this.sysAllFunctions = sysAllFunctions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getLock() {
        return isLock;
    }

    public void setLock(Boolean isLock) {
        this.isLock = isLock;
    }
    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
    public Long getUserExpire() {
        return userExpire;
    }

    public void setUserExpire(Long userExpire) {
        this.userExpire = userExpire;
    }
    public Long getPwdExpire() {
        return pwdExpire;
    }

    public void setPwdExpire(Long pwdExpire) {
        this.pwdExpire = pwdExpire;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public LocalDateTime getCreateItem() {
        return createItem;
    }

    public void setCreateItem(LocalDateTime createItem) {
        this.createItem = createItem;
    }
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", password=" + password +
        ", isLock=" + isLock +
        ", isEnable=" + isEnable +
        ", userExpire=" + userExpire +
        ", pwdExpire=" + pwdExpire +
        ", remark=" + remark +
        ", sortNo=" + sortNo +
        ", telNo=" + telNo +
        ", createUser=" + createUser +
        ", createItem=" + createItem +
        ", orgCode=" + orgCode +
        "}";
    }
}
