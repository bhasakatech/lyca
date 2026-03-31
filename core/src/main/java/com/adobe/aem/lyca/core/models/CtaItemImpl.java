package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
adapters = CtaItem.class)
public class CtaItemImpl implements CtaItem {

    @ValueMapValue
    private String icon;
    @ValueMapValue
    private String label;
    @ValueMapValue
    private String link;

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getLink() {
        return link;
    }
}
