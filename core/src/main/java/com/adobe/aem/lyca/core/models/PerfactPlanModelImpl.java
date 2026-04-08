package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.Resource;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
       adapters = {PerfactPlanModel.class, ComponentExporter.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = PerfactPlanModelImpl.RESOURCE_TYPE
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
          extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class PerfactPlanModelImpl implements PerfactPlanModel {

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/perfact-plan";

    @ValueMapValue
    private String heading;

    @ChildResource(name = "perfectPlanQuestions")
    private List<PerfectPlanQuestion> perfectPlanQuestions;

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public List<PerfectPlanQuestion> getPerfectPlanQuestions() {
        return perfectPlanQuestions;
    }

    @Override
    public String getExportedType() {
        return PerfactPlanModelImpl.RESOURCE_TYPE;
    }
}