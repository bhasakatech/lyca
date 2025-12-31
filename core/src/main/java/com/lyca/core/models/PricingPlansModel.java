package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PricingPlansModel {

    @ValueMapValue
    private String heading;
    @ValueMapValue
    private String planType;
    @ValueMapValue
    private String findPlanLabel;
    @ValueMapValue
    private String findPlanLink;
    @ValueMapValue
    private String viewAllLabel;
    @ValueMapValue
    private String viewAllLink;

    @ChildResource(name = "planItems")
    private List<PlanItem> planItems;

    public String getHeading() {
        return heading;
    }

    public String getPlanType() {
        return planType;
    }

    public String getFindPlanLabel() {
        return findPlanLabel;
    }

    public String getFindPlanLink() {
        return findPlanLink;
    }

    public String getViewAllLabel() {
        return viewAllLabel;
    }

    public String getViewAllLink() {
        return viewAllLink;
    }

    public List<PlanItem> getPlanItems() {
        return planItems;
    }
}
