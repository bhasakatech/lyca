package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PerfactPlanModel;
import com.adobe.aem.lyca.core.models.PerfectPlanQuestion;
import com.adobe.aem.lyca.core.services.PerfactPlanService;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.day.cq.wcm.foundation.TemplatedContainer.log;

/**
 * Implementation of {@link PerfactPlanModel} for the Perfect Plan component.
 *
 * <p>
 * This Sling Model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and is used to expose component dialog properties along with dynamic plan data
 * fetched from Content Fragments using {@link PerfactPlanService}.
 * </p>
 *
 * <p>
 * The model is also exported as JSON using Sling Model Exporter to support
 * SPA (React) integration.
 * </p>
 *
 * <h3>Responsibilities:</h3>
 * <ul>
 *     <li>Read author-configured dialog properties</li>
 *     <li>Fetch plan data from Content Fragment</li>
 *     <li>Expose data for frontend (React SPA)</li>
 * </ul>
 */
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
       adapters = {PerfactPlanModel.class, ComponentExporter.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = PerfactPlanModelImpl.RESOURCE_TYPE
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
          extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
@Slf4j
public class PerfactPlanModelImpl implements PerfactPlanModel {

    /**
     * Resource type of the component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/perfact-plan";

    /**
     * Heading text displayed in the component.
     */
    @ValueMapValue
    private String heading;

    /**
     * List of questions configured as child resources.
     */
    @ChildResource(name = "perfectPlanQuestions")
    private List<PerfectPlanQuestion> perfectPlanQuestions;

    /**
     * Icon path for the component.
     */
    @ValueMapValue
    private String icon;

    /**
     * Title displayed in the component.
     */
    @ValueMapValue
    private String title;

    /**
     * Description text shown below the title.
     */
    @ValueMapValue
    private String description;

    /**
     * Label for the primary CTA button.
     */
    @ValueMapValue
    private String buttonLabel;

    /**
     * Link for the primary CTA button.
     */
    @ValueMapValue
    private String buttonLink;

    /**
     * Label for the "Other Plans" CTA.
     */
    @ValueMapValue
    private String otherPlansLabel;

    /**
     * Link for the "Other Plans" CTA.
     */
    @ValueMapValue
    private String othersPlanLink;

    /**
     * Content Fragment path used to fetch plan data.
     */
    @ValueMapValue
    private String cfPath;

    /**
     * Resource resolver used to access repository resources.
     */
    @SlingObject
    private ResourceResolver resolver;

    /**
     * Service used to fetch plans from Content Fragments.
     */
    @OSGiService
    private PerfactPlanService perfactPlanService;

    /**
     * List of plan items fetched from Content Fragment.
     */
    private List<PerfactPlanItem> plans = new ArrayList<>();

    /**
     * Initializes the model after all injections are complete.
     *
     * <p>
     * Fetches plan data using {@link PerfactPlanService} based on the configured
     * Content Fragment path.
     * </p>
     */
    @PostConstruct
    public void init(){
        if (perfactPlanService == null || resolver == null || cfPath == null || cfPath.isEmpty()) {

            log.warn("Unable to fetch plans. Invalid configuration. cfPath: {}", cfPath);

            return;
        }

        plans = perfactPlanService.getPlans(cfPath, resolver);

        log.info("Fetched {} plans for cfPath: {}", plans.size(), cfPath);
    }

    /**
     * Returns the heading of the component.
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     * Returns the list of configured Perfect Plan questions.
     */
    @Override
    public List<PerfectPlanQuestion> getPerfectPlanQuestions() {
        return perfectPlanQuestions;
    }

    /**
     * Returns the icon path.
     */
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * Returns the title of the component.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description text.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the primary button label.
     */
    @Override
    public String getButtonText() {
        return buttonLabel;
    }

    /**
     * Returns the primary button link.
     */
    @Override
    public String getButtonLink() {
        return buttonLink;
    }

    /**
     * Returns the "Other Plans" label.
     */
    @Override
    public String getOthersPlanText() {
        return otherPlansLabel;
    }

    /**
     * Returns the "Other Plans" link.
     */
    @Override
    public String getOthersPlanLink() {
        return othersPlanLink;
    }

    /**
     * Returns the list of plans fetched from the Content Fragment.
     */
    @Override
    public List<PerfactPlanItem> getPlans() {
        return plans;
    }

    /**
     * Returns the exported resource type for the component.
     */
    @Override
    public String getExportedType() {
        return PerfactPlanModelImpl.RESOURCE_TYPE;
    }
}