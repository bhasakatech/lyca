package com.adobe.aem.lyca.core.models.Impl;

import com.adobe.aem.lyca.core.models.FooterColumn;
import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;
@Model(adaptables = Resource.class,
        adapters = FooterColumn.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FooterColumnImpl implements FooterColumn{
    @ValueMapValue
    private  String title;
    @ChildResource(name = "links")
    private List<FooterLink> links;
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public List<FooterLink> getLinks() {
        return links;
    }
}
