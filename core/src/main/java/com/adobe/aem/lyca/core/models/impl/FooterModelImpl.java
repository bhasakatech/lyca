package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.FooterColumn;
import com.adobe.aem.lyca.core.models.FooterModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;
@Model(
        adaptables = {SlingHttpServletRequest.class,Resource.class},
        adapters = {FooterModel.class, ComponentExporter.class},
        resourceType = FooterModelImpl.RESOURCE_PATH,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class FooterModelImpl implements FooterModel{
    public static final String RESOURCE_PATH = "lyca-spa-react/components/footer";
    @ValueMapValue
    private String lycaLogo;
    @ValueMapValue
    private String lycaDescription;
    @ValueMapValue
    private String copyright;
    @ChildResource(name = "footerLinks")
    private List<FooterColumn> footerLinks;
    @Override
    public String getExportedType() {
        return RESOURCE_PATH;
    }
    @Override
    public String getLycaLogo() {
        return lycaLogo;
    }
    @Override
    public String getLycaDescription() {
        return lycaDescription;
    }
    @Override
    public String getCopyright() {
        return copyright;
    }
    @Override
    public List<FooterColumn> getFooterLinks() {
        return footerLinks;
    }
}
