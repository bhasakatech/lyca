package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables={SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
         resourceType=TopInformationalBannerTextImplModel.RESOURCE_TYPE,
         adapters= ComponentExporter.class)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,extensions=ExporterConstants.SLING_MODEL_EXTENSION)
public class TopInformationalBannerTextImplModel implements TopInformationalBannerText {
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/topInformationalBannerTextComponent";
    @ValueMapValue
    private String text;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getExportedType() {
        return TopInformationalBannerTextImplModel.RESOURCE_TYPE;
    }
}
