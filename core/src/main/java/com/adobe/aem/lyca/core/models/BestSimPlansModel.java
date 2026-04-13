package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;

/**
 * Sling Model interface for the Best SIM Plans component.
 *
 * <p>
 * This model is used to expose component data to the front-end (SPA/React)
 * via JSON export. It provides configuration details such as heading,
 * content fragment path, CTA details, and a list of SIM plan items.
 * </p>
 *
 * <p>
 * The data is typically authored in AEM dialog and/or fetched from
 * Content Fragments under the specified parent path.
 * </p>
 *
 * @author
 *     Abhishek Mishra
 */
public interface BestSimPlansModel extends ComponentExporter {

    /**
     * Returns the heading/title of the Best SIM Plans section.
     *
     * @return the heading text configured by the author
     */
    String getHeading();

    /**
     * Returns the parent path where Content Fragments are stored.
     *
     * <p>
     * This path is used to fetch SIM plan data dynamically from AEM DAM.
     * </p>
     *
     * @return the content fragment parent folder path
     */
    String getFragmentParentPath();

    /**
     * Indicates whether the text/content should be hidden.
     *
     * @return true if text should be hidden, false otherwise
     */
    boolean isHideText();

    /**
     * Returns the Call-To-Action (CTA) label text.
     *
     * @return the CTA button label
     */
    String getCtaLabel();

    /**
     * Returns the CTA link URL.
     *
     * <p>
     * This link is typically used to navigate to another page or section.
     * </p>
     *
     * @return the CTA link path
     */
    String getCtaLink();

    /**
     * Returns the list of SIM plan items.
     *
     * <p>
     * Each item represents a single SIM plan fetched from Content Fragments
     * or configured manually.
     * </p>
     *
     * @return list of BestSimPlanItem objects
     */
    List<BestSimPlanItem> getPlans();
}