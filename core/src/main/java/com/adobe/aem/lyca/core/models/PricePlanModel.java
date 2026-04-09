package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import org.apache.sling.api.resource.LoginException;

import java.util.List;

public interface PricePlanModel extends ComponentExporter {
    String getPricePlanHeading();
    String getPricePlanMonthlyText();
    String getPricePlanYearlyText();
    String getPlanCardsPath();
    String getFindPlanLabel();
    String getFindPlanLink();
    String getAllPlanLabel();

    List<PricePlan> getPlans() throws LoginException;
}
