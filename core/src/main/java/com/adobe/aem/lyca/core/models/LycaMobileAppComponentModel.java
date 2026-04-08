package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface LycaMobileAppComponentModel extends ComponentExporter {
    public String getMainHeading();
    public List<String> getMobileAppFeatures();
    public List<AppStore> getAppStoreComponents();
    public String getQRText();
    public String getQRImage();
    public String getMobileImage();
}
