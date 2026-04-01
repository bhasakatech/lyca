package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

public interface PricePlanModel extends ComponentExporter {
    String getPricePlanHeading();
    String getPricePlanMonthlyText();
    String getPricePlanYearlyText();
    String getPlanCardsPath();
    String getFindPlanLabel();
    String getFindPlanLink();
    String getAllPlanLabel();
    String getAllPlanLink();
}
