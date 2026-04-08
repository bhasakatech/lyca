package com.adobe.aem.lyca.core.models;

public class PricePlan {

    private String planTitle;
    private double priceMonthly;
    private double priceYearly;
    private String dataLimit;
    private String[] features;
    private boolean isPopular;
    private String ctaLabel;
    private String ctaLink;

    public String getPlanTitle()
    {
        return planTitle;
    }
    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public double getPriceMonthly() {
        return priceMonthly;
    }
    public void setPriceMonthly(double priceMonthly) {
        this.priceMonthly = priceMonthly;
    }

    public double getPriceYearly() {
        return priceYearly;
    }
    public void setPriceYearly(double priceYearly) {
        this.priceYearly = priceYearly;
    }

    public String getDataLimit() {
        return dataLimit;
    }
    public void setDataLimit(String dataLimit) {
        this.dataLimit = dataLimit;
    }

    public String[] getFeatures() {
        return features;
    }
    public void setFeatures(String[] features) {
        this.features = features;
    }

    public boolean isPopular() {
        return isPopular;
    }
    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public String getCtaLabel() {
        return ctaLabel;
    }
    public void setCtaLabel(String ctaLabel) {
        this.ctaLabel = ctaLabel;
    }

    public String getCtaLink() {
        return ctaLink;
    }
    public void setCtaLink(String ctaLink) {
        this.ctaLink = ctaLink;
    }
}
