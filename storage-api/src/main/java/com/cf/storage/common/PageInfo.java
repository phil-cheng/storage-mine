package com.cf.storage.common;

import java.util.HashMap;
import java.util.Map;

import com.cf.storage.util.HumpStringUtil;
import com.github.pagehelper.PageRowBounds;

public class PageInfo {

    public PageInfo() {
        super();
    }

    private int rows;
    private int page;
    private String sidx;
    private String sort = "desc";
    private boolean needTotle = true;

    
    public String getSidx() {
        return sidx;
    }

    
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public boolean isNeedTotle() {
        return needTotle;
    }

    public void setNeedTotle(boolean needTotle) {
        this.needTotle = needTotle;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public PageRowBounds getRowBounds() {
        Integer pageSize = this.getRows();
        Integer currentPage = this.getPage();
        int start = 0;
        int limt = pageSize==null?0:pageSize;
        if(pageSize!=null&&currentPage!=null){
            
            if(currentPage >= 1){
                start = (currentPage-1)*pageSize;
            }
        }
        PageRowBounds res = new PageRowBounds(start, limt);
        res.setCount(this.isNeedTotle());
        return res;
    }
    public Map<String,Object> getSortParamMap(){
        Map<String, Object> ret = new HashMap<String,Object>();
        ret.put("sort", this.getSort());
//        ret.put("sidx", HumpStringUtil.humpToUnderLine(this.getSidx()));
        return ret;
    }
}
