package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.FooterColumn;
import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;

/**
 * Implementation class for {@link FooterColumn}.
 * <p>
 * This Sling Model adapts from {@link Resource}
 * and represents a footer column containing a title and a list of links.
 * </p>
 * <p>
 * The links are injected as child resources using
 * {@link ChildResource}.
 * </p>
 * @author Jaya Chandra Reddy
 */
@Model(adaptables = Resource.class,
        adapters = FooterColumn.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FooterColumnImpl implements FooterColumn{
    /** Column title */
    @ValueMapValue
    private  String title;

    /** List of footer links */
    @ChildResource(name = "links")
    private List<FooterLink> links;

    /** {@inheritDoc} */
    @Override
    public String getTitle() {
        return title;
    }

    /** {@inheritDoc} */
    @Override
    public List<FooterLink> getLinks() {
        return links;
    }
}
