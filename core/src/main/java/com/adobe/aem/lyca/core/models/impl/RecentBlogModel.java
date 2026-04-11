package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.beans.BlogData;
import com.adobe.aem.lyca.core.services.BlogService;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import lombok.Getter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {ComponentExporter.class},
        resourceType = RecentBlogModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class RecentBlogModel implements ComponentExporter {

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/recentBlogs";

    @OSGiService
    private BlogService blogService;
    
    @Getter
    @ValueMapValue
    private String headingTitle;

    @ValueMapValue
    private String cfPath;

    @Getter
    private List<BlogData> blogs;

    @PostConstruct
    protected void init() {
        blogs = blogService.getBlogs(cfPath);
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}