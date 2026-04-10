package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
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

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String buttonLabel;

    @ValueMapValue
    private String buttonLink;

    @ValueMapValue
    private String otherPlansLabel;

    @ValueMapValue
    private String othersPlanLink;

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public List<PerfectPlanQuestion> getPerfectPlanQuestions() {
        return perfectPlanQuestions;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getButtonText() {
        return buttonLabel;
    }

    @Override
    public String getButtonLink() {
        return buttonLink;
    }

    @Override
    public String getOthersPlanText() {
        return otherPlansLabel;
    }

    @Override
    public String getOthersPlanLink() {
        return othersPlanLink;
    }

    @Override
    public String getExportedType() {
        return PerfactPlanModelImpl.RESOURCE_TYPE;
    }
}