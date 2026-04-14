package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model representing a single slide in the Hero Carousel component.
 *
 * <p>
 * This model is used to map authored data from AEM (typically from a multifield
 * or child resources) into a structured object that can be exported as JSON
 * and consumed by the front-end (e.g., React SPA).
 * </p>
 *
 * <p>
 * Each slide contains content such as titles, pricing information,
 * call-to-action (CTA), and background styling properties.
 * </p>
 *
 * <p>
 * The model adapts from {@link Resource} and reads values using
 * {@link ValueMapValue} injection.
 * </p>
 */
@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeroSlide {

    /**
     * Optional pre-title text displayed above the main title.
     */
    @ValueMapValue
    private String preTitle;

    /**
     * Main title of the slide.
     */
    @ValueMapValue
    private String title;

    /**
     * Subtitle or description of the slide.
     */
    @ValueMapValue
    private String subtitle;

    /**
     * Price information displayed on the slide.
     */
    @ValueMapValue
    private String price;

    /**
     * Duration associated with the price (e.g., "/30 days").
     */
    @ValueMapValue
    private String duration;

    /**
     * Call-to-action (CTA) button text.
     */
    @ValueMapValue
    private String ctaText;

    /**
     * Call-to-action (CTA) link URL or path.
     */
    @ValueMapValue
    private String ctaLink;

    /**
     * Background image path for the slide.
     */
    @ValueMapValue
    private String bgImage;


    /**
     * Background image color for the slide.
     */
    @ValueMapValue
    private String bgImageColor;


    /**
     * Background color for the slide (used as fallback or styling option).
     */
    @ValueMapValue
    private String bgColor;

    /**
     * @return pre-title text
     */
    public String getPreTitle() {
        return preTitle;
    }

    /**
     * @return main title of the slide
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return subtitle/description of the slide
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * @return price information
     */
    public String getPrice() {
        return price;
    }

    /**
     * @return duration text (e.g., "/30 days")
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @return CTA button text
     */
    public String getCtaText() {
        return ctaText;
    }

    /**
     * @return CTA link path or URL
     */
    public String getCtaLink() {
        return ctaLink;
    }

    /**
     * @return background image path
     */
    public String getBgImage() {
        return bgImage;
    }

    /**
     * @return background color value
     */
    public String getBgColor() {
        return bgColor;
    }

    /**
     * @return background image color value
     */
    public String getBgImageColor() {
        return bgImageColor;
    }

}