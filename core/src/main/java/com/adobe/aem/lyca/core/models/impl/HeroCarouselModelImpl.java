package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.HeroCarouselModel;
import com.adobe.aem.lyca.core.models.HeroSlide;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

/**
 * Sling Model implementation for the Hero Carousel component.
 *
 * <p>
 * This model is responsible for exposing carousel data authored in AEM
 * to the front-end (e.g., React SPA) via JSON export.
 * </p>
 *
 * <p>
 * It retrieves a list of {@link HeroSlide} objects from the child resource
 * named {@code slides}, which is typically configured using a multifield
 * in the AEM dialog.
 * </p>
 *
 * <p>
 * Each {@link HeroSlide} represents a single slide in the carousel,
 * containing properties such as title, subtitle, image, price,
 * and call-to-action details.
 * </p>
 *
 * <p>
 * The model supports Sling Model Exporter, allowing it to be accessed
 * as JSON (e.g., <code>.model.json</code>) for SPA-based rendering.
 * </p>
 */
@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {HeroCarouselModel.class, ComponentExporter.class},
        resourceType = HeroCarouselModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class HeroCarouselModelImpl implements HeroCarouselModel {

    /**
     * Resource type for the Hero Carousel component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/hero-carousel";


    /**
     * Slide time in milliseconds
     */
    @ValueMapValue
    private int slideTime;

    /**
     * List of slides authored under the "slides" child resource.
     *
     * <p>
     * Each child resource is adapted into a {@link HeroSlide} object.
     * This
     * is typically populated via a multifield dialog in AEM.
     * </p>
     */
    @ChildResource(name = "slides")
    private List<HeroSlide> slides;

    /**
     * Returns the list of carousel slides.
     *
     * @return list of {@link HeroSlide} objects
     */
    @Override
    public List<HeroSlide> getSlides() {
        return this.slides;
    }


    /**
     *
     * @return carousel slide time in milliseconds
     */
    @Override
    public int getSlideTime() {
        return slideTime;
    }


    /**
     * Returns the exported resource type of this component.
     *
     * <p>
     * This is used by the front-end to identify the component type
     * when consuming JSON data.
     * </p>
     *
     * @return the component resource type
     */
    @Override
    public String getExportedType() {
        return HeroCarouselModelImpl.RESOURCE_TYPE;
    }
}