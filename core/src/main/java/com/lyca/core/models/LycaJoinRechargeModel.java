package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LycaJoinRechargeModel {

    /* Left Section */
    @ValueMapValue
    private String leftHeading;
    @ValueMapValue
    private String leftSubText;
    @ChildResource(name = "leftItems")
    private List<LeftItemModel> leftItems;

    /* Right Section */
    @ValueMapValue
    private String rightHeading;
    @ValueMapValue
    private String rechargeLabel;
    @ValueMapValue
    private String rechargeLink;
    @ValueMapValue
    private String renewLabel;
    @ValueMapValue
    private String renewLink;
    @ValueMapValue
    private String countryCode;
    @ValueMapValue
    private String phonePlaceholder;
    @ValueMapValue
    private String proceedLabel;
    @ValueMapValue
    private String appText;
    @ValueMapValue
    private String appLinkLabel;
    @ValueMapValue
    private String appLink;

    public String getLeftHeading() {
        return leftHeading;
    }

    public String getLeftSubText() {
        return leftSubText;
    }

    public List<LeftItemModel> getLeftItems() {
        return leftItems;
    }

    public String getRightHeading() {
        return rightHeading;
    }

    public String getRechargeLabel() {
        return rechargeLabel;
    }

    public String getRechargeLink() {
        return rechargeLink;
    }

    public String getRenewLabel() {
        return renewLabel;
    }

    public String getRenewLink() {
        return renewLink;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhonePlaceholder() {
        return phonePlaceholder;
    }

    public String getProceedLabel() {
        return proceedLabel;
    }

    public String getAppText() {
        return appText;
    }

    public String getAppLinkLabel() {
        return appLinkLabel;
    }

    public String getAppLink() {
        return appLink;
    }
}
