package com.adobe.aem.lyca.core.services;

import java.util.List;
import com.adobe.aem.lyca.core.models.PricePlan;

public interface PricePlanService {
    List<PricePlan> getPricePlans(String damPath);
}
