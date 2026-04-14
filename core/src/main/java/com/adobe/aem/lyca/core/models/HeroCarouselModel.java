package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;

/**
 * Sling Model interface for the Hero Carousel component.
 *
 * <p>
 * This model is responsible for exposing carousel data to the front-end
 * (e.g., React SPA) via JSON export. It provides a list of slides that
 * will be rendered as part of the Hero Carousel component.
 * </p>
 *
 * <p>
 * Each slide is represented by a {@link HeroSlide} object, which contains
 * properties such as title, subtitle, image, pricing, and CTA details.
 * </p>
 *
 * <p>
 * The data is typically authored in AEM using a multifield dialog or
 * retrieved from structured content such as Content Fragments.
 * </p>
 *
 * <p>
 * This interface extends {@link ComponentExporter} to support Sling Model
 * JSON export for SPA-based rendering.
 * </p>
 */
public interface HeroCarouselModel extends ComponentExporter {

    /**
     * Returns the list of slides configured for the Hero Carousel.
     *
     * <p>
     * Each slide contains content such as heading, description,
     * background image, and call-to-action details.
     * </p>
     *
     * @return list of {@link HeroSlide} objects
     */
    List<HeroSlide> getSlides();

    /**
     *
     * @return slide time in milliseconds
     */
    int getSlideTime();
}