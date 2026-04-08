package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.BestSimPlanItem;
import com.adobe.aem.lyca.core.models.BestSimPlansModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
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

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/best-sim-plans";

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String fragmentParentPath;

    @ValueMapValue
    private boolean hideText;

    @ValueMapValue
    private String ctaLabel;

    @ValueMapValue
    private String ctaLink;

    @SlingObject
    private ResourceResolver resolver;

    private List<BestSimPlanItem> plans = new ArrayList<>();

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public String getFragmentParentPath() {
        return fragmentParentPath;
    }

    @Override
    public boolean isHideText() {
        return hideText;
    }

    @Override
    public String getCtaLabel() {
        return ctaLabel;
    }

    @Override
    public String getCtaLink() {
        return ctaLink;
    }

    @Override
    public List<BestSimPlanItem> getPlans() {
        return plans;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

    @PostConstruct
    public void init() {
        if (resolver == null || fragmentParentPath == null || fragmentParentPath.isEmpty()) {
            return;
        }

        Resource parentResource = resolver.getResource(fragmentParentPath);
        if (parentResource == null) {
            return;
        }

        Iterator<Resource> resources = parentResource.listChildren();

        while (resources.hasNext()) {
            Resource resource = resources.next();
            if (resource == null) {
                continue;
            }

            Resource masterResource = resource.getChild("jcr:content/data/master");
            if (masterResource == null) {
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

            plans.add(item);
        }
    }


}