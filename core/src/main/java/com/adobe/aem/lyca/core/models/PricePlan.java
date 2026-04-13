package com.adobe.aem.lyca.core.models;

/**
 * POJO (Plain Old Java Object) representing a Price Plan.
 *
 * <p>
 * This class acts as a data holder for price plan details such as
 * title, pricing, features, and call-to-action information.
 * It follows standard Java Bean conventions with private fields
 * and public getter/setter methods.
 * </p>
 * @author Galla Bhanu Prakash
 */
public class PricePlan {
    /** Title of the plan */
    private String planTitle;
    /** Rate of the monthly plan */
    private double priceMonthly;
    /** Rate of the yearly plan */
    private double priceYearly;
    /** Data limit of the plan */
    private String dataLimit;
    /** features of plan */
    private String[] features;
    /** is plan is popular or not */
    private boolean isPopular;
    /** lable of the button */
    private String ctaLabel;
    /** link of the button*/
    private String ctaLink;
    /**
     * @return the plan title
     */
    public String getPlanTitle()
    {
        return planTitle;
    }
    /**
     * @param planTitle the plan title to set
     */
    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }
    /**
     * @return the plan monthly price
     */
    public double getPriceMonthly() {
        return priceMonthly;
    }
    /**
     * @param priceMonthly the plan monthly to set
     */
    public void setPriceMonthly(double priceMonthly) {
        this.priceMonthly = priceMonthly;
    }
    /**
     * @return the plan yearly price
     */
    public double getPriceYearly() {
        return priceYearly;
    }
    /**
     * @param priceYearly the plan yearly to set
     */
    public void setPriceYearly(double priceYearly) {
        this.priceYearly = priceYearly;
    }
    /**
     * @return the plan data limit
     */
    public String getDataLimit() {
        return dataLimit;
    }
    /**
     * @param dataLimit the data limit to set
     */
    public void setDataLimit(String dataLimit) {
        this.dataLimit = dataLimit;
    }
    /**
     * @return the plan features
     */
    public String[] getFeatures() {
        return features;
    }
    /**
     * @param features the plan features to set
     */
    public void setFeatures(String[] features) {
        this.features = features;
    }
    /**
     * @return the plan is popular or not
     */
    public boolean isPopular() {
        return isPopular;
    }
    /**
     * @param popular the plan is popular to set
     */
    public void setPopular(boolean popular) {
        isPopular = popular;
    }
    /**
     * @return the button label
     */
    public String getCtaLabel() {
        return ctaLabel;
    }
    /**
     * @param ctaLabel the button label to set
     */
    public void setCtaLabel(String ctaLabel) {
        this.ctaLabel = ctaLabel;
    }
    /**
     * @return the button link
     */
    public String getCtaLink() {
        return ctaLink;
    }
    /**
     * @param ctaLink the button link to set
     */
    public void setCtaLink(String ctaLink) {
        this.ctaLink = ctaLink;
    }
}
