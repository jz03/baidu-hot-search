package com.jz.baiduHotSearch.pojo;

import java.util.Date;

public class HotSearchInfo {
    private int id;
    private int branchId;
    private Date recordDate;
    private String img;
    private int hotScore;
    private String query;
    private int index;
    private String url;
    private String word;
    private String desc;

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

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getRecordDate() {
        return recordDate;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getHotScore() {
        return hotScore;
    }

    public void setHotScore(int hotScore) {
        this.hotScore = hotScore;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "HotSearchInfo{" +
                "id=" + id +
                ", branchId=" + branchId +
                ", recordDate=" + recordDate +
                ", img='" + img + '\'' +
                ", hotScore=" + hotScore +
                ", query='" + query + '\'' +
                ", index=" + index +
                ", url='" + url + '\'' +
                ", word='" + word + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
