package com.atom.smart.utlis;

import java.util.HashMap;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-10-03 21:05
 **/
public class ListParamsInfo {

    private Integer firstIndex;
    private Integer perPageCount;
    private HashMap<String,Object> mapQuery;
    private String[] ascs;
    private String[] descs;

    public String[] getAscs() {
        return ascs;
    }

    public void setAscs(String... ascs) {
        this.ascs = ascs;
    }

    public String[] getDescs() {
        return descs;
    }

    public void setDescs(String... descs) {
        this.descs = descs;
    }

    public Integer getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(Integer firstIndex) {
        this.firstIndex = firstIndex;
    }

    public Integer getPerPageCount() {
        return perPageCount;
    }

    public void setPerPageCount(Integer perPageCount) {
        this.perPageCount = perPageCount;
    }

    public HashMap<String, Object> getMapQuery() {
        return mapQuery;
    }

    public void setMapQuery(HashMap<String, Object> mapQuery) {
        this.mapQuery = mapQuery;
    }
}
