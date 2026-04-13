package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.CountryModel;
import com.adobe.aem.lyca.core.models.LycaWorldWideModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.osgi.service.component.annotations.ComponentPropertyType;

import java.util.List;

/**
 * Implementation of {@link LycaWorldWideModel}.
 *
 * <p>
 * This Sling Model retrieves Worldwide component data from the repository
 * and exposes it as JSON using Sling Model Exporter for frontend applications
 * such as React SPA.
 * </p>
 * @author Galla Bhanu Prakash
 */
@Model(
        /**
         * Defines the adaptable sources for this Sling Model.
         *
         * {@link SlingHttpServletRequest}
         * Used when the model is adapted from a request.
         * It helps in rendering the UI component on the webpage
         * and provides access to request-related data.
         *
         * {@link Resource}
         * Used when the model is adapted from a resource.
         * It provides access to the content (JCR data) of the component.
        */
        adaptables= {Resource.class, SlingHttpServletRequest.class},
        /** Interfaces this model adapts to*/
        adapters = {LycaWorldWideModel.class, ComponentExporter.class},
        /** Resource type mapping for UI component
         * it helps to connect the component by using path*/
        resourceType =LycaWorldWideModelImpl.RESOURCE_TYPE,
        /**Optional injection strategy to avoid injection failures*/
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        /**it enables the JSON exporter for Single Page Application*/
        name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class LycaWorldWideModelImpl implements LycaWorldWideModel {
    /**
     * Resource type of the Lyca Worldwide component.
     * it is the path of the component in AEM
     */
    public static final String RESOURCE_TYPE="lyca-spa-react/components/lycaworldwide";

    /** Main heading of the worldwide section */
    @ValueMapValue
    private String worldwideHeading;

    /** Subheading of the worldwide section */
    @ValueMapValue
    private String worldwideSubHeading;

    /** Placeholder text for search input */
    @ValueMapValue
    private String searchPlaceholderText;

    /**
     * List of country items authored as child resources.
     */
    @ChildResource
    private List<CountryModel> countries;

    /** Button label*/
    @ValueMapValue
    private String ctaLabel;

    /** Button link*/
    @ValueMapValue
    private String ctaLink;

    /** {@inheritDoc} */
    @Override
    public String getWorldwideHeading() {
        return worldwideHeading;
    }

    /** {@inheritDoc} */
    @Override
    public String getWorldwideSubHeading() {
        return worldwideSubHeading;
    }

    /** {@inheritDoc} */
    @Override
    public String getSearchPlaceholderText() {
        return searchPlaceholderText;
    }

    /** {@inheritDoc} */
    @Override
    public List<CountryModel> getCountries() {
        return countries;
    }

    /** {@inheritDoc} */
    @Override
    public String getCtaLabel() {
        return ctaLabel;
    }

    /** {@inheritDoc} */
    @Override
    public String getCtaLink() {
        return ctaLink;
    }

    /**
     * Returns the exported resource type for the component.
     * @return the resource type
     */
    @Override
    public String getExportedType() {
        return LycaWorldWideModelImpl.RESOURCE_TYPE;
    }
}