package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;

/**
 * Sling Model interface for the Lyca Join component.
 *
 * <p>
 * This model is used to expose component data to the front-end via JSON export.
 * It provides content related to join section such as title, description,
 * CTA items, form fields, and promotional text.
 * </p>
 *
 * <p>
 * The data is typically authored in AEM and mapped from component dialog
 * or Content Fragment fields.
 * </p>
 *
 * @author Bhaskar
 * @since 1.0
 */
public interface LycaJoinModel extends ComponentExporter {

    /**
     * Returns the main title of the join component.
     *
     * @return title text
     */
    String getTitle();

    /**
     * Returns the description for the join component.
     *
     * @return description text
     */
    String getDescription();

    /**
     * Returns the list of CTA items displayed in the component.
     *
     * @return list of {@link LycaCtaItemModel}
     */
    List<LycaCtaItemModel> getCtaItems();

    /**
     * Returns the heading text for the form section.
     *
     * @return heading text
     */
    String getHeading();

    /**
     * Returns the list of CTA buttons.
     *
     * @return list of {@link LycaCtaButton}
     */
    List<LycaCtaButton> getCtas();

    /**
     * Returns the placeholder text for the input field.
     *
     * @return placeholder text
     */
    String getPlaceholder();

    /**
     * Returns the submit button label.
     *
     * @return submit button text
     */
    String getSubmitText();

    /**
     * Returns the submit action link.
     *
     * @return submit link URL
     */
    String getSubmitLink();

    /**
     * Returns the promotional text displayed in the component.
     *
     * @return promotion text
     */
    String getPromotionText();
}