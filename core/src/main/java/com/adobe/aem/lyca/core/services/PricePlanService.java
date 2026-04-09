package com.adobe.aem.lyca.core.services;

import com.adobe.aem.lyca.core.models.PricePlan;
import org.apache.sling.api.resource.LoginException;

import java.util.List;

public interface PricePlanService {
    List<PricePlan> getPricePlans(String damPath) throws LoginException;
}
