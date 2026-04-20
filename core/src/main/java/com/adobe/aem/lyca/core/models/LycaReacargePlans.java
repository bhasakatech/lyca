package com.adobe.aem.lyca.core.models;

/**
 * Represents a single recharge plan item.
 * <p>
 * This interface provides details about individual recharge plans
 * such as tab association, data benefits, pricing, and validity.
 * </p>
 * <p>
 * It is typically used as a child model within
 * {@link LycaReacargeModel}.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
public interface LycaReacargePlans {
    /**
     * Returns the tab key associated with the plan.
     * @return the tab key
     */
    String getTabKey();

    /**
     * Returns the data benefits of the plan.
     * @return the data value
     */
    String getData();

    /**
     * Returns the price of the plan.
     * @return the price value
     */
    String getPrice();

    /**
     * Returns the validity of the plan.
     * @return the validity duration
     */
    String getValidity();
}
