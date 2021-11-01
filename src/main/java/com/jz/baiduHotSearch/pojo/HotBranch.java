package com.jz.baiduHotSearch.pojo;

import java.util.Date;

public class HotBranch {
    private int id;
    private int branchId;
    private int hotInfoId;
    private int hotScore;
    private int index;
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getHotInfoId() {
        return hotInfoId;
    }

    public void setHotInfoId(int hotInfoId) {
        this.hotInfoId = hotInfoId;
    }

    public int getHotScore() {
        return hotScore;
    }

    public void setHotScore(int hotScore) {
        this.hotScore = hotScore;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
