package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaCtaButton;
import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model implementation for CTA Button.
 *
 * <p>
 * This model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and maps AEM authored dialog fields into a CTA button representation.
 * </p>
 *
 * <p>
 * It is used as part of multifield configurations inside AEM components,
 * where each CTA button contains display text and a navigation link.
 * </p>
 * @since 1.0
 */
@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = LycaCtaButton.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Getter
public class LycaCtaButtonImpl implements LycaCtaButton {

    /**
     * Display text of the CTA button.
     * Example: "Buy Now", "Learn More"
     */
    @ValueMapValue
    private String text;

    /**
     * Navigation link associated with the CTA button.
     * Example: "/content/lyca/en/buy"
     */
    @ValueMapValue
    private String link;
}