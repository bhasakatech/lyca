package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = {Resource.class , SlingHttpServletRequest.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SignupModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String placeholder;

    @ValueMapValue
    private String buttonText;

    @ValueMapValue
    private String description;

    public String getTitle() {
        return title;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getDescription() {
        return description;
    }
}
