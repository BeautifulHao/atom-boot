package com.atom.smart.utlis;

import java.util.List;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-10-03 21:09
 **/
public class ListWithCount<E> {

    private List<E> lists;
    private long count;
    private long totalPageCount;

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<E> getLists() {
        return lists;
    }

    public void setLists(List<E> lists) {
        this.lists = lists;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
