package com.adobe.aem.lyca.core.models;


import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;
/**

 * PrepaidSimPlansModel interface
 *
 * <p>
 * This interface represents the model for the Prepaid SIM Plans component.
 * It provides all the required data to render the component such as heading,
 * description, toggle buttons, content fragment data, and existing customer operation.
 * </p>

 */
public interface PrepaidSimPlansModel extends ComponentExporter {

    /**
     * Returns the main heading of the component.
     *
     * @return heading text
     */
    String getHeading();

    /**
     * Returns the description text displayed below the heading.
     *
     * @return description text
     */
    String getDescription();

    /**
     * Returns the list of toggle buttons (e.g., categories).
     *
     * @return array of toggle button labels
     */
    String[] getToggleSwitchButton();

    /**
     * Returns the parent path of the Content Fragments.
     *
     * @return fragment parent path
     */
    String getFragmentParentPath();

    /**
     * Returns the list of prepaid SIM plan items fetched from Content Fragments.
     *
     * @return list of PrepaidSimPlanItem
     */
    List<PrepaidSimPlanItem> getPrepaidSimPlanItems();

    /**
     * Returns the title for existing customers section.
     *
     * @return existing customer title
     */
    String getExistingCustomTitle();

    /**
     * Returns the list of options for existing customers.
     *
     * @return array of existing plan options
     */
    String[] getExistingPlanOptions();

    /**
     * Returns the CTA link for existing customers.
     *
     * @return CTA link URL
     */
    String getCtaLink();

}

