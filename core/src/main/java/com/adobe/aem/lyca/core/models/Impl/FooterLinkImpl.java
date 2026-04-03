package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
@Model(adaptables = Resource.class,
        adapters = FooterLink.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FooterLinkImpl implements FooterLink{
    @ValueMapValue
    private String navigationLabel;
    @ValueMapValue
    private String navigationURL;
    @Override
    public String getNavigationLabel() {
        return navigationLabel;
    }
    @Override
    public String getNavigationURL() {
        return navigationURL;
    }
}
