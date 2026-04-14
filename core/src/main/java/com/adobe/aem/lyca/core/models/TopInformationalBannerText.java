package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

/**
 * Interface for the Top Informational Banner Text Sling Model.
 *
 * <p>
 * This interface defines the contract for retrieving data from the
 * Top Informational Banner Text component used in the AEM application.
 * It extends {@link ComponentExporter} to support JSON export
 * through the Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The component provides:
 * </p>
 * <ul>
 *     <li>Banner text content displayed in the top informational banner</li>
 * </ul>
 *
 * @author
 * Adobe
 */

public interface TopInformationalBannerText extends ComponentExporter {

    /**
     * Returns the text content of the top informational banner.
     *
     * @return banner text string
     */
    String getText();
}
