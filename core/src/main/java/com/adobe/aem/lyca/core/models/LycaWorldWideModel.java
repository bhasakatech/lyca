package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;
/**
 * Interface representing the Lyca WorldWide component model.
 *
 * <p>
 * Provides methods to retrieve all authorable properties and
 * country-related data required for rendering the component.
 * This model is also exposed as JSON via the Sling Model Exporter
 * for frontend applications such as React SPA.
 * </p>
 */
public interface LycaWorldWideModel extends ComponentExporter {
    /**
     * Retrieves the main heading of the Worldwide section.
     * @return the worldwide heading
     */
    String getWorldwideHeading();

    /**
     * Retrieves the subheading of the Worldwide section.
     * @return the worldwide subheading
     */
    String getWorldwideSubHeading();

    /**
     * Retrieves the placeholder text for the search input.
     * @return the search placeholder text
     */
    String getSearchPlaceholderText();

    /**
     * Retrieves the list of countries to be displayed.
     * @return list of {@link CountryModel} objects
     */
    List<CountryModel> getCountries();

    /**
     * Retrieves the label for the call-to-action button.
     * @return the CTA label
     */
    String getCtaLabel();

    /**
     * Retrieves the link for the call-to-action button.
     * @return the CTA link
     */
    String getCtaLink();
}