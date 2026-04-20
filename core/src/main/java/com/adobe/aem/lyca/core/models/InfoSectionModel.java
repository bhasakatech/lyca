package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

public interface InfoSectionModel extends ComponentExporter {

    String getHeading();
    String getDescription();

}
