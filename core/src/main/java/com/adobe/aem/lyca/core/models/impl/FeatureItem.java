package com.adobe.aem.lyca.core.models.impl;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FeatureItem {

    @ValueMapValue

    private String icon;
    @ValueMapValue
    private String iconLink;
    @ValueMapValue
    private String featureHeading;
    @ValueMapValue
    private String featureDescription;

    public String getIcon() {
        return icon;
    }


    public String getIconLink() {
        return iconLink;
    }

    public String getFeatureHeading() {
        return featureHeading;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }
}
