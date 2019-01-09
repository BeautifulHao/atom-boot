package com.atom.smart.support;

import java.io.Serializable;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-08 09:43
 **/
public class jsonResult implements Serializable {

    private boolean result;
    private String message;
    private Object data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public jsonResult() {
        this.result=true;
    }

    public jsonResult(boolean result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

}
