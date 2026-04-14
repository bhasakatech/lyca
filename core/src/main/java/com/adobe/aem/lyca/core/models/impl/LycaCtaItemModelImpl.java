package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaCtaItemModel;
import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model implementation for CTA Item.
 *
 * <p>
 * This model represents an individual CTA item used in AEM components.
 * It adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and maps authored dialog fields into a structured Java object.
 * </p>
 *
 * <p>
 * Each CTA item typically contains:
 * icon, label, and navigation link, which are used to render
 * dynamic CTA sections in the frontend (SPA/React).
 * </p>
 * @since 1.0
 */
@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = LycaCtaItemModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Getter
public class LycaCtaItemModelImpl implements LycaCtaItemModel {

    /**
     * Icon representation for the CTA item.
     * Example: image path or icon class (e.g., "icon-phone", "/content/dam/icon.png")
     */
    @ValueMapValue
    private String icon;

    /**
     * Display label for the CTA item.
     * Example: "Call Us", "Email Support"
     */
    @ValueMapValue
    private String label;

    /**
     * Navigation link associated with the CTA item.
     * Example: "/content/lyca/en/contact"
     */
    @ValueMapValue
    private String link;
}