package com.adobe.aem.lyca.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaFeatureComponent.class, ComponentExporter.class},
        resourceType = LycaFeatureComponentImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LycaFeatureComponentImpl implements LycaFeatureComponent {

    public static final String RESOURCE_TYPE="lyca-spa-react/components/lycafeaturecomponent";

    @ValueMapValue
    private String lycaFeatureComponentMainHeading;

    @ChildResource(name = "lycaFeatureCards")
    private List<FeatureItem> items;


    @Override
    public String getLycaFeatureComponentMainHeading() {
        return lycaFeatureComponentMainHeading;
    }

    @Override
    public List<FeatureItem> getItems() {
        return items;
    }

    @Override
    public String getExportedType() {
        return LycaFeatureComponentImpl.RESOURCE_TYPE;
    }

}