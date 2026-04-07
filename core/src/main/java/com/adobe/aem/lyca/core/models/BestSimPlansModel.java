package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface BestSimPlansModel extends ComponentExporter {
    String getHeading();
    String getFragmentParentPath();
    boolean isHideText();
    String getCtaLabel();
    String getCtaLink();
    List<BestSimPlanItem> getPlans();
}