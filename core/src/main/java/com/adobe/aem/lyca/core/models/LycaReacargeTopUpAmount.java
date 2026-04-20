package com.adobe.aem.lyca.core.models;

/**
 * Represents a top-up amount option.
 * <p>
 * This interface provides details for predefined
 * recharge amounts displayed in the top-up section.
 * </p>
 * <p>
 * Each top-up option includes a value and a label
 * for display purposes in the UI.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
public interface LycaReacargeTopUpAmount {
    /**
     * Returns the amount value.
     * @return the amount
     */
    String getAmount();

    /**
     * Returns the label for the amount.
     * @return the label text
     */
    String getLabel();
}
