package com.adobe.aem.lyca.core.models;

/**
 * Represents a tab in the recharge plans section.
 * <p>
 * This interface defines the structure for plan tabs
 * used to categorize different recharge plans.
 * </p>
 * <p>
 * Each tab is identified by a label and a unique key
 * used for filtering plans in the UI.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
public interface LycaReacargePlanTab {
    /**
     * Returns the display label of the tab.
     * @return the tab label
     */
    String getTabLabel();

    /**
     * Returns the unique key of the tab.
     * @return the tab key
     */
    String getTabKey();
}
