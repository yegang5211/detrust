package com.hankal.detrust.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "带有分页查询的操作", description = "手机号码，设备标识，TOKEN")
public class QueryRequestVo extends RequestVo {
    @ApiModelProperty(required = false, value = "一次抓取数据的条数", name = "pageSize", example = "选填")
    private int pageSize = 10;
    @ApiModelProperty(required = false, value = "从第几页索引开始抓取", name = "pageIndex", example = "选填")
    private int pageIndex = 0; // 0代表第一页

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
