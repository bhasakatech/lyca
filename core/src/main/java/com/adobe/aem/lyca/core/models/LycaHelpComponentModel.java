package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;


/**
 * Interface for the Lyca Help Component Sling Model.
 *
 * <p>
 * This interface defines the contract for retrieving data from the
 * Lyca Help Component used in the AEM application.
 * It extends {@link ComponentExporter} to enable JSON export
 * through the Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The component provides:
 * </p>
 * <ul>
 *     <li>Main heading of the help component</li>
 *     <li>List of helping icon items displayed in the component</li>
 * </ul>
 *
 * @author
 * Adobe
 */

public interface LycaHelpComponentModel extends ComponentExporter {

    /**
     * Returns the heading text of the Lyca Help Component.
     *
     * @return heading string
     */
    String getHeading();

    /**
     * Returns the list of helping icon items configured for this component.
     *
     * @return list of helper component models
     */
    List<HelperComponentsModel> getHelpingIcons();
}


