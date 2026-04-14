package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaExclusiveOffers;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Implementation class for the {@link LycaExclusiveOffers} model.
 * <p>
 * This Sling Model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and provides the data required for the Lyca Exclusive Offers component.
 * </p>
 * <p>
 * The properties are injected using {@link ValueMapValue} from the AEM resource.
 * This class also supports JSON export using Sling Model Exporter.
 * </p>
 * @author Jaya Chandra Reddy
 */

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaExclusiveOffers.class, ComponentExporter.class},
        resourceType = LycaExclusiveOffersImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class LycaExclusiveOffersImpl implements LycaExclusiveOffers{
    /**
     * Resource type for the Lyca Exclusive Offers component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycaexclusiveoffers";

    /** Heading text of the component */
    @ValueMapValue
    private String heading;

    /** Placeholder text for input field */
    @ValueMapValue
    private String inputPlaceholderText;

    /** Label for the CTA button */
    @ValueMapValue
    private String ctaLabel;

    /** Link for the CTA button */
    @ValueMapValue
    private String ctaLink;

    /** Description text of the component */
    @ValueMapValue
    private String description;

    /** {@inheritDoc} */
    @Override
    public String getHeading() {
        return heading;
    }

    /** {@inheritDoc} */
    @Override
    public String getInputPlaceholderText() {
        return inputPlaceholderText;
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

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the exported resource type for this component.
     * @return the resource type string
     */
    @Override
    public String getExportedType() {
        return LycaExclusiveOffersImpl.RESOURCE_TYPE;
    }
}
