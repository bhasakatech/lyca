package com.adobe.aem.lyca.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface LycaFeatureComponent extends ComponentExporter {
    String getLycaFeatureComponentMainHeading();
    List<FeatureItem> getItems();
}
