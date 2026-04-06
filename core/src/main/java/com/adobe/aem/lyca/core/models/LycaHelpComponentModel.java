package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface LycaHelpComponentModel extends ComponentExporter {
    String getHeading();
    List<ComponentsModel> getHelpingIcons();
}


