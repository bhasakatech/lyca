package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface LycaJoinModel extends ComponentExporter {

    String getTitle();
    String getDescription();

    List<CtaItem> getCtaItems();

}
