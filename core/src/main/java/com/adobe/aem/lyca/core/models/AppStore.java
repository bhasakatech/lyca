package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
        )
public class AppStore {
    @ValueMapValue
    private String appStoreIcon;

    @ValueMapValue
    private String appStoreLink;

    public String getAppStoreIcon() {
        return appStoreIcon;
    }

    public String getAppStoreLink() {
        return appStoreLink;
    }
}
