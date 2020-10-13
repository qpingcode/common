package me.qping.common.model;

import java.util.List;

/**
 * Created by QPing on 2015/12/11.
 */
public class PageData<T> {
    int total;
    List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
