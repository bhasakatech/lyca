package com.adobe.aem.lyca.core.models;

/**
 * Model interface representing a CTA item with icon, label, and link.
 *
 * <p>
 * This interface is used to define individual CTA items displayed in the
 * Lyca Join component. Each item typically includes an icon, a label,
 * and a navigation link.
 * </p>
 *
 * <p>
 * The data is authored in the AEM component dialog and mapped to this model
 * for rendering dynamic CTA elements on the frontend.
 * </p>
 *
 * @since 1.0
 */
public interface LycaCtaItemModel {

    /**
     * Returns the icon associated with the CTA item.
     *
     * @return icon path, URL, or identifier (e.g., image path or icon class)
     */
    String getIcon();

    /**
     * Returns the label text of the CTA item.
     *
     * @return label text displayed to the user
     */
    String getLabel();

    /**
     * Returns the navigation link for the CTA item.
     *
     * @return URL or path to which the CTA item redirects
     */
    String getLink();
}