package com.adobe.aem.lyca.core.models;

/**
 * Represents a single navigation link in the Footer component.
 * <p>
 * This interface defines the methods to retrieve the label and URL
 * for each footer navigation link authored in AEM.
 * </p>
 * @author Jaya Chandra Reddy
 */
public interface FooterLink {
    /**
     * Returns the navigation label for the link.
     * @return the navigation label
     */
    public String getNavigationLabel();

    /**
     * Returns the navigation URL for the link.
     * @return the navigation URL
     */
    public String getNavigationURL();
}
