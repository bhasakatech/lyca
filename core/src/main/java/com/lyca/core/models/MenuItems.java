package com.lyca.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)   
public class MenuItems {

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String url;

    // Getters
    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
    
}
