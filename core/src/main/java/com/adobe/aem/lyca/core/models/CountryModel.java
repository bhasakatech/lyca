package com.adobe.aem.lyca.core.models;
/**
 * Interface representing a Country model.
 *
 * <p>
 * Defines the structure for country-related data such as
 * logo, link, and name. This model is typically used within
 * the Worldwide component to display a list of countries.
 * </p>
 */
public interface CountryModel {
    /**
     * Retrieves the country logo path or URL.
     * @return the country logo
     */
    String getCountryLogo();

    /**
     * Retrieves the link associated with the country logo.
     * @return the logo link
     */
    String getLogoLink();

    /**
     * Retrieves the name of the country.
     * @return the country name
     */
    String getCountryName();
}