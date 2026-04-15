package com.adobe.aem.lyca.core.models;

/**
 * Model interface representing a Call-To-Action (CTA) button.
 *
 * <p>
 * This interface is used to define the structure of CTA buttons
 * configured in the Lyca Join component. Each CTA button typically
 * contains display text and a corresponding navigation link.
 * </p>
 *
 * <p>
 * The data is authored in AEM component dialog and mapped to this model
 * for rendering in the frontend.
 * </p>
 *
 * @since 1.0
 */
public interface LycaCtaButton {

    /**
     * Returns the display text of the CTA button.
     *
     * @return button label text (e.g., "Recharge", "Renewal plan")
     */
    String getText();

    /**
     * Returns the navigation link associated with the CTA button.
     *
     * @return URL or path to which the button redirects
     */
    String getLink();
}