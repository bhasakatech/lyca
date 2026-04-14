package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
/**
 * Implementation class for {@link FooterLink}.
 * <p>
 * This Sling Model adapts from {@link Resource}
 * and retrieves navigation link properties from AEM.
 * </p>
 * <p>
 * The properties are injected using
 * {@link ValueMapValue}.
 * </p>
 * @author Jaya Chandra Reddy
 */
@Model(adaptables = Resource.class,
        adapters = FooterLink.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class FooterLinkImpl implements FooterLink{
    /** Navigation label */
    @ValueMapValue
    private String navigationLabel;

    /** Navigation URL */
    @ValueMapValue
    private String navigationURL;

    /** {@inheritDoc} */
    @Override
    public String getNavigationLabel() {
        return navigationLabel;
    }

    /** {@inheritDoc} */
    @Override
    public String getNavigationURL() {
        return navigationURL;
    }
}
