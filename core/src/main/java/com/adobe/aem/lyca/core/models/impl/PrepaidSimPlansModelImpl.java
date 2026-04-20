package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.BestSimPlanItem;
import com.adobe.aem.lyca.core.models.PrepaidSimPlanItem;
import com.adobe.aem.lyca.core.models.PrepaidSimPlansModel;
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
import java.util.*;

/**
 * Sling Model implementation for the Prepaid SIM Plans component.
 *
 * <p>This class reads component authoring properties and loads prepaid SIM plan
 * data from the configured Content Fragment parent path. It prepares the final
 * list of {@link PrepaidSimPlanItem} objects for frontend rendering and JSON export.</p>
 */

@Slf4j
@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {PrepaidSimPlansModel.class, ComponentExporter.class},
        resourceType = PrepaidSimPlansModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class PrepaidSimPlansModelImpl implements PrepaidSimPlansModel {

    /**
     * Resource type for the Best SIM Plans component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/prepaid-sim-plans";


    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String[] toggleSwitchButton;

    @ValueMapValue
    private String fragmentParentPath;


    /* existing custom  */

    @ValueMapValue
    private String existingCustomTitle;

    @ValueMapValue
    private String[] existingPlanOptions;

    @ValueMapValue
    private String ctaLink;

    @SlingObject
    private ResourceResolver resolver;

    private List<PrepaidSimPlanItem> prepaidSimPlanItems = new ArrayList<>();


    /**
     * Returns the list of prepaid SIM plan items loaded from Content Fragments.
     *
     * @return list of plan items
     */
    @Override
    public List<PrepaidSimPlanItem> getPrepaidSimPlanItems() {
        return prepaidSimPlanItems;
    }

    /**
     * Returns the component heading.
     *
     * @return heading text
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     * Returns the component description.
     *
     * @return description text
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the toggle button values used in the UI.
     *
     * @return array of toggle labels
     */
    @Override
    public String[] getToggleSwitchButton() {
        return toggleSwitchButton;
    }

    /**
     * Returns the parent path that contains the Content Fragments.
     *
     * @return fragment parent path
     */
    @Override
    public String getFragmentParentPath() {
        return fragmentParentPath;
    }

    /**
     * Returns the title shown for the existing customer section.
     *
     * @return existing customer title
     */
    @Override
    public String getExistingCustomTitle() {
        return existingCustomTitle;
    }

    /**
     * Returns the existing customer option values.
     *
     * @return array of existing customer options
     */
    @Override
    public String[] getExistingPlanOptions() {
        return existingPlanOptions;
    }


    /**
     * Returns the CTA link for the existing customer section.
     *
     * @return CTA link
     */
    @Override
    public String getCtaLink() {
        return ctaLink;
    }

    /**
     * Returns the exported resource type of this component.
     *
     * @return component resource type
     */
    @Override
    public String getExportedType() {
        return PrepaidSimPlansModelImpl.RESOURCE_TYPE;
    }

    /**
     * Initializes the model after dependency injection.
     *
     * <p>This method reads all child Content Fragments under the configured parent path,
     * extracts values from their master data node, maps them to
     * {@link PrepaidSimPlanItem} objects, and stores them in the plan items list.</p>
     */
    @PostConstruct
    public void init() {
        log.debug("Initializing PrepaidSimPlansModelImpl with fragmentParentPath: {}", fragmentParentPath);
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

            PrepaidSimPlanItem item = new PrepaidSimPlanItem();
            item.setName(resource.getName());
            item.setPlanLabel(vm.get("planLabel",""));
            item.setPlanTitle(vm.get("planTitle",""));
            item.setSubTitle(vm.get("subTitle",""));

            item.setPrice(vm.get("price",""));

            item.setValidity(vm.get("validity",""));
            String[] features = vm.get("features", String[].class);

            if (features != null) {
                item.setFeatures(Arrays.asList(features));
            } else {
                item.setFeatures(Collections.emptyList());
            }
            item.setCategory(vm.get("category",""));
            item.setBuyNowCtaLabel(vm.get("buyNowCtaLabel",""));
            item.setBuyNowCtaLink(vm.get("buyNowCtaLink",""));
            item.setAddToBasketCtaLabel(vm.get("addToBasketCtaLabel",""));
            item.setAddToBasketCtaLink(vm.get("addToBasketCtaLink",""));
            item.setViewMoreLabel(vm.get("viewMoreLabel",""));
            item.setViewMoreLink(vm.get("viewMoreLink",""));
            item.setFooterText(vm.get("footerText",""));
            item.setPath(resource.getPath());
            item.setImage(vm.get("image",""));
            item.setSimImage(vm.get("simImage",""));
            item.setNetworkImage(vm.get("networkImage",""));

            prepaidSimPlanItems.add(item);
            log.debug("Creating plan item for resource: {}", resource.getName());
        }
    }
}
