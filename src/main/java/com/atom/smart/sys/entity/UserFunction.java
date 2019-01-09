package com.atom.smart.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 人员菜单
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@TableName("sp_user_function")
public class UserFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserFunction() {
    }

    public UserFunction(String id, String userCode, String functionCode, LocalDateTime attachDatetime) {
        this.id = id;
        this.userCode = userCode;
        this.functionCode = functionCode;
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
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
        return "UserFunction{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", functionCode=" + functionCode +
        ", attachDatetime=" + attachDatetime +
        "}";
    }
}
