package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_sys_organization")
public class SysOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    public SysOrganization() {

    }

    public SysOrganization(String id, String code, String name, Integer configType) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.configType = configType;
    }

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 父节点
     */
    private String parentCode;

    /**
     * 类型：集团、公司、部门、虚拟
     */
    private Integer configType;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 管理人员
     */
    private String manager;

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
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "SysOrganization{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", parentCode=" + parentCode +
        ", configType=" + configType +
        ", status=" + status +
        ", sortNo=" + sortNo +
        ", address=" + address +
        ", tel=" + tel +
        ", email=" + email +
        ", manager=" + manager +
        "}";
    }
}
