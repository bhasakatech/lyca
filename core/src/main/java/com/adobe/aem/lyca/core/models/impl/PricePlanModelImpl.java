package com.adobe.aem.lyca.core.models.impl;
import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanModel;
import com.adobe.aem.lyca.core.services.PricePlanService;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.List;
/**
 * Implementation of {@link PricePlanModel}.
 *
 * <p>
 * This Sling Model retrieves Price Plan component data from the JCR
 * and exposes it as JSON using Sling Model Exporter for frontend
 * applications such as React SPA.
 * </p>
 *
 * @author Galla Bhanu Prakash
 */
@Model(
        /**
         * Defines the adaptable sources for this Sling Model.
         *
         * SlingHttpServletRequest:
         * Used when the model is adapted from a request.
         * It helps in rendering the UI component on the webpage
         * and provides access to request-related data.
         *
         * Resource:
         * Used when the model is adapted from a resource.
         * It provides access to the content (JCR data) of the component.
        */
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        /** Interfaces this model adapts to*/
        adapters = {PricePlanModel.class, ComponentExporter.class},
        /** Resource type mapping for UI component
         * it helps to connect the component by using path*/
        resourceType = PricePlanModelImpl.RESOURCE_TYPE,
        /**Optional injection strategy to avoid injection failures*/
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        /**it enables the JSON exporter for Single Page Application*/
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class PricePlanModelImpl implements PricePlanModel {
    /**
     * Resource type of the Price Plan component.
     * it is the path of the component in AEM
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/priceplan";

    /** Price plan section heading */
    @ValueMapValue
    private String pricePlanHeading;
    /** Monthly toggle label */
    @ValueMapValue
    private String pricePlanMonthlyText;
    /** Yearly toggle label */
    @ValueMapValue
    private String pricePlanYearlyText;
    /** Path to plan cards data */
    @ValueMapValue
    private String planCardsPath;
    /** Label for "Find Plan" button */
    @ValueMapValue
    private String findPlanLabel;
    /** Link for "Find Plan" button */
    @ValueMapValue
    private String findPlanLink;
    /** Label for "View All Plans" button */
    @ValueMapValue
    private String allPlanLabel;
    /**
     * Service used to fetch price plan data.
     */
    @OSGiService
    private PricePlanService pricePlanService;

    /**
     * {@inheritDoc}*/
    @Override
    public String getPricePlanHeading() {
        return pricePlanHeading;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public String getPricePlanMonthlyText() {
        return pricePlanMonthlyText;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public String getPricePlanYearlyText() {
        return pricePlanYearlyText;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public String getPlanCardsPath() {
        return planCardsPath;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public String getFindPlanLabel() {
        return findPlanLabel;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public String getFindPlanLink() {
        return findPlanLink;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public String getAllPlanLabel() {
        return allPlanLabel;
    }
    /**
     * {@inheritDoc}*/
    @Override
    public List<PricePlan> getPlans() throws LoginException {
        return pricePlanService.getPricePlans(planCardsPath);
    }

    /**
     * Returns the exported resource type for SPA framework.
     *
     * @return resource type string
     */
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}