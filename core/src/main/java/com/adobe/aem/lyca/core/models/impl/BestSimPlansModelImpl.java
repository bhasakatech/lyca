package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.BestSimPlanItem;
import com.adobe.aem.lyca.core.models.BestSimPlansModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Sling Model implementation for the Best SIM Plans component.
 *
 * <p>This model is responsible for exposing author-configured component data
 * and dynamically loading SIM plan details from Content Fragments stored
 * under the configured parent path.</p>
 *
 * <p>The exported JSON is consumed by the front-end application, such as
 * a React SPA, for rendering the Best SIM Plans section.</p>
 *
 * <p>This model adapts from both {@link SlingHttpServletRequest} and
 * {@link Resource}, and supports JSON export through the Sling Model Exporter.</p>
 *
 * <p>Expected responsibilities of this model:</p>
 * <ul>
 *   <li>Read authored component properties such as heading, CTA label, and CTA link</li>
 *   <li>Resolve the configured Content Fragment parent folder path</li>
 *   <li>Iterate through fragment resources under the folder</li>
 *   <li>Read fragment data from {@code jcr:content/data/master}</li>
 *   <li>Transform fragment data into a list of {@link BestSimPlanItem} objects</li>
 * </ul>
 */
@Slf4j
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

    /**
     * Resource type for the Best SIM Plans component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/best-sim-plans";

    /**
     * Section heading authored for the component.
     */
    @ValueMapValue
    private String heading;

    /**
     * Parent DAM path containing the Content Fragments for SIM plans.
     */
    @ValueMapValue
    private String fragmentParentPath;

    /**
     * Flag indicating whether descriptive text should be hidden in the UI.
     */
    @ValueMapValue
    private boolean hideText;

    /**
     * Label text for the call-to-action button or link.
     */
    @ValueMapValue
    private String ctaLabel;

    /**
     * Target path or URL for the call-to-action.
     */
    @ValueMapValue
    private String ctaLink;

    /**
     * Resource resolver used to fetch the parent folder resource
     * and iterate through child Content Fragments.
     */
    @SlingObject
    private ResourceResolver resolver;

    /**
     * List of SIM plan items prepared from Content Fragment data.
     */
    private List<BestSimPlanItem> plans = new ArrayList<>();

    /**
     * Returns the authored heading for the Best SIM Plans section.
     *
     * @return the heading text
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     * Returns the configured parent path containing SIM plan Content Fragments.
     *
     * @return the Content Fragment parent folder path
     */
    @Override
    public String getFragmentParentPath() {
        return fragmentParentPath;
    }

    /**
     * Indicates whether text content should be hidden.
     *
     * @return {@code true} if text should be hidden; otherwise {@code false}
     */
    @Override
    public boolean isHideText() {
        return hideText;
    }

    /**
     * Returns the configured CTA label.
     *
     * @return the CTA label text
     */
    @Override
    public String getCtaLabel() {
        return ctaLabel;
    }

    /**
     * Returns the configured CTA link.
     *
     * @return the CTA link path or URL
     */
    @Override
    public String getCtaLink() {
        return ctaLink;
    }

    /**
     * Returns the list of SIM plan items built from Content Fragment data.
     *
     * @return list of {@link BestSimPlanItem} objects
     */
    @Override
    public List<BestSimPlanItem> getPlans() {
        return plans;
    }

    /**
     * Returns the exported resource type of this component.
     *
     * @return the component resource type
     */
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

    /**
     * Initializes the model after dependency injection is complete.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Validates that the resource resolver and fragment parent path are available</li>
     *   <li>Resolves the parent resource from the configured DAM path</li>
     *   <li>Iterates through each child resource under the parent folder</li>
     *   <li>Looks for fragment data under {@code jcr:content/data/master}</li>
     *   <li>Reads the fragment properties from the {@link ValueMap}</li>
     *   <li>Creates a {@link BestSimPlanItem} for each valid fragment</li>
     *   <li>Adds the item to the {@code plans} list</li>
     * </ol>
     *
     * <p>If any required dependency or resource is missing, the method exits safely
     * without throwing an exception.</p>
     */
    @PostConstruct
    public void init() {
        log.debug("Initializing BestSimPlansModelImpl with fragmentParentPath: {}", fragmentParentPath);
        if (resolver == null || fragmentParentPath == null || fragmentParentPath.isEmpty()) {
            log.warn("ResourceResolver or fragmentParentPath is null/empty");
            return;
        }

        Resource parentResource = resolver.getResource(fragmentParentPath);
        if (parentResource == null) {
            log.warn("No resource found at path: {}", fragmentParentPath);
            return;
        }

        Iterator<Resource> resources = parentResource.listChildren();

        while (resources.hasNext()) {
            log.debug("Processing child resources under: {}", fragmentParentPath);
            Resource resource = resources.next();
            if (resource == null) {
                log.debug("Skipping null resource");
                continue;
            }

            Resource masterResource = resource.getChild("jcr:content/data/master");
            if (masterResource == null) {
                log.debug("Master data not found for resource: {}", resource.getPath());
                continue;
            }

            ValueMap vm = masterResource.getValueMap();

            BestSimPlanItem item = new BestSimPlanItem(
                    resource.getName(),
                    vm.get("name", ""),
                    vm.get("monthlyCost", ""),
                    vm.get("data", ""),
                    vm.get("minutes", ""),
                    vm.get("texts", ""),
                    vm.get("contract", ""),
                    vm.get("annualPriceRise", ""),
                    vm.get("freeEuRoaming", "")

            );
            log.debug("Creating plan item for resource: {}", resource.getName());

            plans.add(item);
        }
        log.info("Total plans loaded: {}", plans.size());
    }
}