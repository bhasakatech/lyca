package com.adobe.aem.lyca.core.models.impl;
import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanModel;
import com.adobe.aem.lyca.core.services.PricePlanService;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.List;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {PricePlanModel.class, ComponentExporter.class},
        resourceType = PricePlanModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class PricePlanModelImpl implements PricePlanModel {
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/priceplan";
    @ValueMapValue
    private String pricePlanHeading;
    @ValueMapValue
    private String pricePlanMonthlyText;
    @ValueMapValue
    private String pricePlanYearlyText;
    @ValueMapValue
    private String planCardsPath;
    @ValueMapValue
    private String findPlanLabel;
    @ValueMapValue
    private String findPlanLink;
    @ValueMapValue
    private String allPlanLabel;

    @OSGiService
    private PricePlanService pricePlanService;

    @Override
    public String getPricePlanHeading() {
        return pricePlanHeading;
    }
    @Override
    public String getPricePlanMonthlyText() {
        return pricePlanMonthlyText;
    }
    @Override
    public String getPricePlanYearlyText() {
        return pricePlanYearlyText;
    }
    @Override
    public String getPlanCardsPath() {
        return planCardsPath;
    }
    @Override
    public String getFindPlanLabel() {
        return findPlanLabel;
    }
    @Override
    public String getFindPlanLink() {
        return findPlanLink;
    }
    @Override
    public String getAllPlanLabel() {
        return allPlanLabel;
    }
    @Override
    public List<PricePlan> getPlans() {
        return pricePlanService.getPricePlans(planCardsPath);
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}