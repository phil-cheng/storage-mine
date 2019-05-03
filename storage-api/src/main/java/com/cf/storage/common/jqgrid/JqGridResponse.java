package com.cf.storage.common.jqgrid;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cf.storage.common.PageInfo;
import com.github.pagehelper.Page;

public class JqGridResponse {

    private int total;
    private long records;
    private boolean success = true;
    private Integer page;
    private List<Object> rows;

    public JqGridResponse(int total, long records, boolean success, Integer page, List<Object> rows) {
        super();
        this.total = total;
        this.records = records;
        this.success = success;
        this.page = page;
        this.rows = rows;
    }

    public JqGridResponse() {
        super();
    }

    public JqGridResponse(Page<Object> page,PageInfo pageInfo) {
        super();
        if (page != null) {
            this.setTotal(pageCount(page.getTotal(), pageInfo.getRows()==0?1:pageInfo.getRows()));
            this.setPage(pageInfo.getPage());
            this.setRecords(page.getTotal());
            rows = new ArrayList<Object>();
            for(Object t : page){
                if(t==null){
                    continue;
                }
                rows.add(t);
            }
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Object> getRows() {
        return rows;
    }

    public void setRows(List<Object> rows) {
        this.rows = rows;
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }
    private static int pageCount(Long total,int pageSize){
        int ret = (int) ((total-1+pageSize)/pageSize);
        return ret;
    }
}
