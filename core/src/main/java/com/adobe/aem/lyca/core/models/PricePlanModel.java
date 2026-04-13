package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import org.apache.sling.api.resource.LoginException;

import java.util.List;
/**
 * Interface representing the Price Plan component model.
 * <p>
 * Provides methods to retrieve all authorable properties and
 * price plan data required for rendering the component.
 * This model is also exposed as JSON via the Sling Model Exporter
 * for frontend consumption (e.g., React SPA).
 * </p>
 */
public interface PricePlanModel extends ComponentExporter {
    /**
     * Retrieves the heading of the Price Plan section.
     * @return the price plan heading
     */
    String getPricePlanHeading();
    /**
     * Retrieves the label for the monthly pricing option.
     * @return the monthly toggle text
     */
    String getPricePlanMonthlyText();
    /**
     * Retrieves the label for the yearly pricing option.
     * @return the yearly toggle text
     */
    String getPricePlanYearlyText();
    /**
     * Retrieves the repository path containing plan card data.
     * @return the plan cards content path
     */
    String getPlanCardsPath();
    /**
     * Retrieves the label for the "Find Plan" action.
     * @return the find plan label
     */
    String getFindPlanLabel();
    /**
     * Retrieves the navigation link for the "Find Plan" action.
     * @return the find plan link
     */
    String getFindPlanLink();
    /**
     * Retrieves the label for the "View All Plans" action.
     * @return the all plans label
     */
    String getAllPlanLabel();

    /**
     * Retrieves the list of available price plans.
     * @return a list of {@link PricePlan} objects
     * @throws LoginException if an error occurs while accessing repository data
     */
    List<PricePlan> getPlans() throws LoginException;
}
