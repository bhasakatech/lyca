package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.BestSimPlansModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {BestSimPlansModel.class, ComponentExporter.class},
        resourceType = BestSimPlansModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class BestSimPlansModelImpl implements BestSimPlansModel {

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/best-sim-plans";

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String fragmentParentPath;

    @ValueMapValue
    private boolean hideText;

    @ValueMapValue
    private String ctaLabel;

    @ValueMapValue
    private String ctaLink;

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public String getFragmentParentPath() {
        return fragmentParentPath;
    }

    @Override
    public boolean isHideText() {
        return hideText;
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
        return RESOURCE_TYPE;
    }
}