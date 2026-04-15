package com.adobe.aem.lyca.core.services;

import com.adobe.aem.lyca.core.models.PricePlan;
import org.apache.sling.api.resource.LoginException;

import java.util.List;
/**
 * Service interface for retrieving Price Plan data.
 *
 * <p>
 * This service is responsible for fetching price plan details
 * from the repository (e.g., Content Fragments in DAM) and
 * converting them into {@link PricePlan} objects.
 * </p>
 */
public interface PricePlanService {
    /**
     * Retrieves a list of price plans from the specified DAM path.
     *
     * @param damPath the repository path where price plan data is stored
     * @return list of {@link PricePlan} objects
     * @throws LoginException if service user authentication fails
     */
    List<PricePlan> getPricePlans(String damPath) throws LoginException;
}
