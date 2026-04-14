package com.adobe.aem.lyca.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

/**
 * Interface for the Lyca Feature Component Sling Model.
 *
 * <p>
 * This interface defines the contract for retrieving data from the
 * Lyca Feature Component used in the AEM SPA React application.
 * It extends {@link ComponentExporter} to support JSON export
 * through the Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The component provides:
 * </p>
 * <ul>
 *     <li>Main heading of the feature component</li>
 *     <li>List of feature card items</li>
 * </ul>
 *
 * @author
 * Adobe
 */

public interface LycaFeatureComponent extends ComponentExporter {

    /**
     * Returns the main heading text of the Lyca Feature Component.
     *
     * @return main heading string
     */
    String getLycaFeatureComponentMainHeading();

    /**
     * Returns the list of feature card items configured for this component.
     *
     * @return list of feature items
     */
    List<FeatureItem> getItems();
}
