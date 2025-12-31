package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PlanItem {

    @ValueMapValue
    private String planTitle;

    @ValueMapValue
    private String planPrice;

    @ValueMapValue
    private String planData;

    @ChildResource(name = "planFeatures")
    private List<Resource> planFeatures; 

    @ValueMapValue
    private boolean planHighlight;

    @ValueMapValue
    private String ctaLabel;

    @ValueMapValue
    private String ctaLink;


    // Getters
    public String getPlanTitle() {
        return planTitle;
    }

    public String getPlanPrice() {
        return planPrice;
    }

    public String getPlanData() {
        return planData;
    }

    public List<Resource> getPlanFeatures() {
        return planFeatures;
    }

    public boolean isPlanHighlight() {
        return planHighlight;
    }

    public String getCtaLabel() {
        return ctaLabel;
    }

    public String getCtaLink() {
        return ctaLink;
    }
}

