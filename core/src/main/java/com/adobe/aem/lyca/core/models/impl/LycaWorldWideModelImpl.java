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

@Model(
        adaptables= {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaWorldWideModel.class, ComponentExporter.class},
        resourceType =LycaWorldWideModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class LycaWorldWideModelImpl implements LycaWorldWideModel {
    public static final String RESOURCE_TYPE="lyca-spa-react/components/lycaworldwide";
    @ValueMapValue
    private String worldwideHeading;

    @ValueMapValue
    private String worldwideSubHeading;

    @ValueMapValue
    private String searchPlaceholderText;

    @ChildResource
    private List<CountryModel> countries;

    @ValueMapValue
    private String ctaLabel;

    @ValueMapValue
    private String ctaLink;

    @Override
    public String getWorldwideHeading() {
        return worldwideHeading;
    }

    @Override
    public String getWorldwideSubHeading() {
        return worldwideSubHeading;
    }

    @Override
    public String getSearchPlaceholderText() {
        return searchPlaceholderText;
    }

    @Override
    public List<CountryModel> getCountries() {
        return countries;
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
    public String getExportedType() {
        return LycaWorldWideModelImpl.RESOURCE_TYPE;
    }
}