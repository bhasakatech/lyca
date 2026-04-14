package com.adobe.aem.lyca.core.models;

import java.util.List;

/**
 * Represents a column in the Footer component.
 *
 * <p>
 * Each footer column contains a title and a list of
 * {@link FooterLink} items.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */

public interface FooterColumn {
    /**
     * Returns the title of the footer column.
     * @return the column title
     */

    public String getTitle();
    /**
     * Returns the list of footer links under this column.
     * @return list of {@link FooterLink}
     */
    public List<FooterLink> getLinks();
}
