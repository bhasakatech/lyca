package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.SpaNavigationLocale;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model implementation for representing locale information
 * in the SPA Navigation component.
 *
 * <p>
 * This model adapts from a {@link Resource} and provides locale-specific
 * details such as language and region configured at the component level.
 * These values are typically authored via dialog fields in AEM and used
 * in the frontend (e.g., React SPA) for displaying selected locale.
 * </p>
 *
 * <p>
 * Example:
 * <ul>
 *     <li>language = "EN"</li>
 *     <li>region = "US"</li>
 * </ul>
 * This can be rendered in UI as: <b>EN 🇺🇸</b>
 * </p>
 *
 * <p>
 * Injection Strategy: OPTIONAL<br>
 * If properties are not present on the resource, null values will be returned.
 * </p>
 */
@Model(
        adaptables = Resource.class,
        adapters = SpaNavigationLocale.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SpaNavigationLocaleImpl implements SpaNavigationLocale {

    /**
     * Language code configured for the locale.
     * Example: EN, HI, FR
     */
    @ValueMapValue
    private String language;

    /**
     * Region (country) code associated with the locale.
     * Example: US, IN, GB
     */
    @ValueMapValue
    private String region;

    /**
     * Returns the language code
     */
    @Override
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the region code.
     */
    @Override
    public String getRegion() {
        return region;
    }
}
