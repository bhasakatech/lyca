package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.models.impl.PerfactPlanItem;
import com.adobe.aem.lyca.core.services.PerfactPlanService;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.day.cq.wcm.foundation.TemplatedContainer.log;

/**
 * Implementation of {@link PerfactPlanService}.
 *
 * <p>
 * This service is responsible for fetching plan data from Content Fragments
 * stored under a given parent path in the AEM repository.
 * </p>
 *
 * <p>
 * Each child resource under the provided path is treated as an individual
 * Content Fragment. The service reads structured data from the
 * <code>jcr:content/data/master</code> node of each fragment.
 * </p>
 *
 * <h3>Responsibilities:</h3>
 * <ul>
 *     <li>Validate input parameters</li>
 *     <li>Traverse Content Fragment folder</li>
 *     <li>Extract plan data (name, data, price)</li>
 *     <li>Return list of {@link PerfactPlanItem}</li>
 * </ul>
 *
 * <p>
 * This service is registered as an OSGi component and can be injected
 * into Sling Models or other services.
 * </p>
 */
@Component(service = PerfactPlanService.class)
@Slf4j
public class PerfactPlanServiceImpl implements PerfactPlanService {

    /**
     * Path to the master data node inside a Content Fragment.
     */
    private static final String DATA_NODE_PATH = "jcr:content/data/master";

    /**
     * Fetches plan data from Content Fragments located under the given parent path.
     *
     * <p>
     * The method performs the following steps:
     * <ul>
     *     <li>Validates input parameters</li>
     *     <li>Fetches parent resource using {@link ResourceResolver}</li>
     *     <li>Iterates through child resources</li>
     *     <li>Skips invalid or system nodes (like jcr:content)</li>
     *     <li>Reads data from {@value #DATA_NODE_PATH}</li>
     *     <li>Maps values to {@link PerfactPlanItem}</li>
     * </ul>
     * </p>
     */
    @Override
    public List<PerfactPlanItem> getPlans(String cfParentPath, ResourceResolver resolver) {

        List<PerfactPlanItem> plans = new ArrayList<>();

        log.info("Starting plan fetch for CF path: {}", cfParentPath);

        if (resolver == null) {
            log.error("ResourceResolver is null. Cannot fetch plans.");
            return plans;
        }

        if (cfParentPath == null || cfParentPath.isEmpty()) {
            log.warn("Invalid CF parent path: {}", cfParentPath);
            return plans;
        }

        Resource parentResource = resolver.getResource(cfParentPath);
        if(parentResource == null){
            log.error("No resource found at path: {}", cfParentPath);
            return plans;
        }

        log.debug("Parent resource found: {}", parentResource.getPath());

        Iterator<Resource> resources = parentResource.listChildren();
        while (resources.hasNext()){
            Resource resource = resources.next();
            if (resource == null){
                log.warn("Encountered null child resource, skipping...");
                continue;
            }
            if(resource.getPath().contains("jcr:content")){
                log.debug("Skipping system node: {}", resource.getPath());
                continue;
            }

            log.debug("Processing resource: {}", resource.getPath());

            Resource dataRes  = resource.getChild(DATA_NODE_PATH);

            if (dataRes == null) {
                log.warn("Missing data node for resource: {}", resource.getPath());
                continue;
            }

            ValueMap vm = dataRes.getValueMap();

            PerfactPlanItem item = new PerfactPlanItem(
                    vm.get("name", ""),
                    vm.get("data", ""),
                    vm.get("price", "")
            );

            plans.add(item);
            log.info("Added plan item: {}", item);
        }
        log.info("Successfully fetched {} plans from path: {}", plans.size(), cfParentPath);
        return plans;
    }
}