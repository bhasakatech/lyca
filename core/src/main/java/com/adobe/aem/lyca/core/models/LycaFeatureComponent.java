package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface LycaFeatureComponent extends ComponentExporter {
    String getHeading();
    List<FeatureItem> getItems();
}
