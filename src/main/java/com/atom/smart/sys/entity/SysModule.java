package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-11-05
 */
@TableName("sp_sys_module")
public class SysModule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    public SysModule() {
    }

    public SysModule(String id, String moduleCode, String moduleName, Boolean isSystem, String icon, Integer sortNo) {
        this.id = id;
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.isSystem = isSystem;
        this.icon = icon;
        this.sortNo = sortNo;
    }

    /**
     * 编码
     */
    private String moduleCode;

    /**
     * 系统名称
     */
    private String moduleName;

    /**
     * 是否系统内置
     */
    private Boolean isSystem;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 创建时间
     */
    private LocalDate createDate;

    /**
     * 系列号
     */
    private String seriesCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public Boolean getSystem() {
        return isSystem;
    }

    public void setSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    @Override
    public String toString() {
        return "SysModule{" +
        "id=" + id +
        ", moduleCode=" + moduleCode +
        ", moduleName=" + moduleName +
        ", isSystem=" + isSystem +
        ", icon=" + icon +
        ", sortNo=" + sortNo +
        ", createDate=" + createDate +
        ", seriesCode=" + seriesCode +
        "}";
    }
}
