package com.adobe.aem.lyca.core.models;

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

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaHelpComponentModel.class, ComponentExporter.class},
        resourceType = LycaHelpComponentModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
          extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class LycaHelpComponentModelImpl implements LycaHelpComponentModel {

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycahelpcomponent";

    @ValueMapValue
    private String heading;

    @ChildResource
    private List<HelperComponentsModel> helpingIcons;

    @Override
    public String getExportedType() {
        return LycaHelpComponentModelImpl.RESOURCE_TYPE;
    }


    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public List<HelperComponentsModel> getHelpingIcons() {
        return helpingIcons;
    }

}
