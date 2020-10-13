package me.qping.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QPing on 2015/12/11.
 */
public class CommonPage {

    int pageSize;
    int pageNo;
    int start;
    int end;

    public Map<String,Object> toMap(){
        int start = pageNo * pageSize;
        int end = ( pageNo + 1) * pageSize;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageSize", pageSize);
        map.put("pageNo", pageNo);
        map.put("start", start);
        map.put("end", end);
        return map;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
