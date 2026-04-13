package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
/**
 * Represents the model interface for the Lyca Exclusive Offers component.
 * <p>
 * This interface defines the methods required to retrieve the data
 * authored for the Exclusive Offers component in AEM.
 * </p>
 * <p>
 * It extends {@link ComponentExporter} to allow exporting the component
 * as JSON for frontend frameworks like React.
 * </p>
 * @author Jaya Chandra Reddy
 */
public interface LycaExclusiveOffers extends ComponentExporter {
    /**
     * Returns the heading text of the component.
     * @return the heading string
     */
    public String getHeading();

    /**
     * Returns the placeholder text for the input field.
     * @return the input placeholder text
     */
    public String getInputPlaceholderText() ;

    /**
     * Returns the label for the Call-To-Action (CTA) button.
     * @return the CTA label
     */
    public String getCtaLabel();

    /**
     * Returns the link associated with the CTA button.
     * @return the CTA link URL
     */
    public String getCtaLink();

    /**
     * Returns the description text of the component.
     * @return the description string
     */
    public String getDescription();
}
