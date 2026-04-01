package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;
public interface FooterModel extends ComponentExporter {
    public String getLycaLogo() ;
    public String getLycaDescription();
    public String getCopyright();
    public List<FooterColumn> getFooterLinks();
}
