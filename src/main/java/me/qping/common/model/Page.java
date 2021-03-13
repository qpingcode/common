package me.qping.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QPing on 2015/12/11.
 */
public class Page {

    public static final int PAGE_SIZE_DEFAULT = 10;
    public static final String PAGE_ORDER_DEFAULT_DIRECTION = "asc";

    String order;

    int totalCount = -1;
    // pageNo 从 1 开始
    int pageNo = 1;
    int pageSize = PAGE_SIZE_DEFAULT;

    String orderColumn;
    String orderDirection;

    int prePage;
    int nextPage;

    public Page(){
    }

    public Page(int pageNo, int pageSize){
        this.pageNo = pageNo < 1 ? 1 : pageNo;
        this.pageSize = pageSize <= 0 ? PAGE_SIZE_DEFAULT : pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount < 0 ? -1 : totalCount;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    // 通过一些参数计算出来的东西
    public int getTotalPage() {
        if(totalCount == -1){
            throw new RuntimeException("请先查询总记录数！");
        }

        return (totalCount - 1)/pageSize + 1;
    }

    public int getNextPage(){
        int totalPage = getTotalPage();
        return (totalPage - pageNo > 1) ? pageNo + 1 : totalPage;
    }

    public int getPrePage(){
        int totalPage = getTotalPage();
        return pageNo > 1 ? (pageNo -1 ) : 1;
    }

    public int getLimit() {
        return pageSize;
    }

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

    public int getStart() {
        return getOffset();
    }

    public int getEnd() {
        return getOffset() + getLimit();
    }

    public Map<String,Object> toMap(){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageSize", getLimit());
        map.put("order", order);
        map.put("start", getStart());
        map.put("end", getEnd());

        return map;
    }

}
