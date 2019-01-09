package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    public SysRole() {
    }

    public SysRole(String id, String code, String name, Integer configType, Integer sortNo, Boolean status, String remark) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.configType = configType;
        this.sortNo = sortNo;
        this.status = status;
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
    private String name;

    /**
     * 类型：系统、业务
     */
    private Integer configType;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 状态
     */
    private Boolean status;

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
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", code=" + code +
        ", name=" + name +
        ", configType=" + configType +
        ", sortNo=" + sortNo +
        ", status=" + status +
        ", remark=" + remark +
        "}";
    }
}
