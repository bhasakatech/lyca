package com.adobe.aem.lyca.core.services;

import com.adobe.aem.lyca.core.models.impl.PerfactPlanItem;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface PerfactPlanService {
    List<PerfactPlanItem> getPlans(String cfParentPath, ResourceResolver resolver);
}
