package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

public interface LycaExclusiveOffers extends ComponentExporter {
    public String getHeading();
    public String getInputPlaceholderText() ;
    public String getCtaLabel();
    public String getCtaLink();
    public String getDescription();
}
