package com.adobe.aem.lyca.core.models;
/**
 * Interface representing a single call support option.
 *
 * <p>This model defines the structure for call support details
 * used in the Help & Support component. Each option typically
 * includes a label describing the type of support and the
 * corresponding contact number.</p>
 */
public interface CallSupportOption {
    /**
     * Returns the label describing the support option.
     *
     * <p>Example: "Customer Care", "Technical Support"</p>
     *
     * @return the label text
     */
    String getLabel();

    /**
     * Returns the phone number associated with the support option.
     *
     * @return the support contact number
     */
    String getNumber();
}
