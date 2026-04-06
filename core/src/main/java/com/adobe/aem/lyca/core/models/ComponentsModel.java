package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables= {Resource.class, SlingHttpServletRequest.class},
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ComponentsModel {

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String iconLink;

    @ValueMapValue
    private String iconHeading;

    public String getIcon() {
        return icon;
    }

    public String getIconHeading() {
        return iconHeading;
    }

    public String getIconLink() {
        return iconLink;
    }
}
