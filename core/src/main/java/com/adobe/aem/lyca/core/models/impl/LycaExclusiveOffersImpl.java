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
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycaexclusiveoffers";
    @ValueMapValue
    private String heading;
    @ValueMapValue
    private String inputPlaceholderText;
    @ValueMapValue
    private String ctaLabel;
    @ValueMapValue
    private String ctaLink;
    @ValueMapValue
    private String description;

    @Override
    public String getHeading() {
        return heading;
    }
    @Override
    public String getInputPlaceholderText() {
        return inputPlaceholderText;
    }
    @Override
    public String getCtaLabel() {
        return ctaLabel;
    }
    @Override
    public String getCtaLink() {
        return ctaLink;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public String getExportedType() {
        return LycaExclusiveOffersImpl.RESOURCE_TYPE;
    }
}
