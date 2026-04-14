package com.adobe.aem.lyca.core.models.impl;

public class PerfactPlanItem {
     private String planName;
     private String data;
     private String price;

    public PerfactPlanItem(String planName, String data, String price) {
        this.planName = planName;
        this.data = data;
        this.price = price;
    }

    public String getPlanName() { return planName; }
    public String getData() { return data; }
    public String getPrice() { return price; }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
