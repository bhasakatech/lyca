package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.CountryModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
/**
 * Implementation of {@link CountryModel}.
 *
 * <p>
 * This Sling Model adapts from a {@link Resource} or
 * {@link SlingHttpServletRequest} and maps country-related
 * properties authored in the repository.
 * It is typically used as a child model within the
 * Lyca Worldwide component.
 * </p>
 */
@Model(
        /**
         * Defines the adaptable sources for this Sling Model.
         *
         * {@link SlingHttpServletRequest}
         * Used when the model is adapted from a request.
         * It helps in rendering the UI component on the webpage
         * and provides access to request-related data.
         *
         * {@link Resource}
         * Used when the model is adapted from a resource.
         * It provides access to the content (JCR data) of the component.
        */
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        /** Interfaces this model adapts to*/
        adapters = CountryModel.class,
        /**Optional injection strategy to avoid injection failures*/
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CountryModelImpl implements CountryModel {
    /** Path or URL of the country logo */
    @ValueMapValue
    private String countryLogo;
    /** Path or URL of the country logo link */
    @ValueMapValue
    private String logoLink;
    /** name of the country */
    @ValueMapValue
    private String countryName;

    /** {@inheritDoc} */
    @Override
    public String getCountryLogo() {
        return countryLogo;
    }

    /** {@inheritDoc} */
    @Override
    public String getLogoLink() {
        return logoLink;
    }

    /** {@inheritDoc} */
    @Override
    public String getCountryName() {
        return countryName;
    }
}