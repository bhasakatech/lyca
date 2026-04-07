package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class PricePlanCFModel {

    @ValueMapValue
    private String planTitle;

    @ValueMapValue
    private double priceMonthly;

    @ValueMapValue
    private double priceYearly;

    @ValueMapValue
    private String dataLimit;

    @ValueMapValue
    private String[] features;

    @ValueMapValue
    private boolean isPopular;

    @ValueMapValue
    private String ctaLabel;

    @ValueMapValue
    private String ctaLink;

    public String getPlanTitle() { return planTitle; }
    public double getPriceMonthly() { return priceMonthly; }
    public double getPriceYearly() { return priceYearly; }
    public String getDataLimit() { return dataLimit; }
    public String[] getFeatures() { return features; }
    public boolean isPopular() { return isPopular; }
    public String getCtaLabel() { return ctaLabel; }
    public String getCtaLink() { return ctaLink; }
}
