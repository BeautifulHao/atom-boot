package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_sys_function")
public class SysFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    public SysFunction() {
    }

    public SysFunction(String id, String code, String parentCode, String name, Integer configType, String url, String permissions, Boolean status, Integer sortNo, String moduleCode, String icon, String remark) {
        this.id = id;
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.configType = configType;
        this.url = url;
        this.permissions = permissions;
        this.status = status;
        this.sortNo = sortNo;
        this.moduleCode = moduleCode;
        this.icon = icon;
        this.remark = remark;
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
    private String parentCode;

    /**
     * 上级编码
     */
    private String name;

    /**
     * 类型：菜单、分组、功能
     */
    private Integer configType;

    /**
     * 地址
     */
    private String url;

    /**
     * 权限点
     */
    private String permissions;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 模块编码
     */
    private String moduleCode;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

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
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
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
    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysFunction{" +
        "id=" + id +
        ", code=" + code +
        ", parentCode=" + parentCode +
        ", name=" + name +
        ", configType=" + configType +
        ", url=" + url +
        ", permissions=" + permissions +
        ", status=" + status +
        ", sortNo=" + sortNo +
        ", moduleCode=" + moduleCode +
        ", icon=" + icon +
        ", remark=" + remark +
        "}";
    }
}
