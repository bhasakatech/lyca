package com.adobe.aem.lyca.core.models;

import com.adobe.aem.lyca.core.models.impl.PerfactPlanItem;
import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;

public interface PerfactPlanModel extends ComponentExporter{
    String getHeading();
    List<PerfectPlanQuestion> getPerfectPlanQuestions();
    String getIcon();
    String getTitle();
    String getDescription();
    String getButtonText();
    String getButtonLink();
    String getOthersPlanText();
    String getOthersPlanLink();
    List<PerfactPlanItem> getPlans();
}