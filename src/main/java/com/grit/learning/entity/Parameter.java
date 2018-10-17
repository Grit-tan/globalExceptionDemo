package com.grit.learning.entity;

import java.util.HashMap;
import java.util.Map;

public class Parameter {
    private String companyId;

    private String category;

    private String platform;

    private String shopName;

    private String shopId;

    private String startTime;

    private String endTime;

    private Map<String, String> parameterMap = new HashMap<>();

    public Parameter put(String key, String value) {
        parameterMap.put(key, value);
        return this;
    }

    public Parameter remove(String key) {
        parameterMap.remove(key);
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
