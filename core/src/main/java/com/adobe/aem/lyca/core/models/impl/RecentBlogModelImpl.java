package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.RecentBlogModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
@Model(adaptables = {Resource.class,SlingHttpServletRequest.class,},
        adapters = {RecentBlogModel.class, ComponentExporter.class},
        resourceType = RecentBlogModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions =  ExporterConstants.SLING_MODEL_EXTENSION)
public class RecentBlogModelImpl implements RecentBlogModel {

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/recentBlogs";

    @ValueMapValue
    private String headingTitle;

    @ValueMapValue
    private String cfPath;

    @Override
    public String getHeadingTitle() {
        return headingTitle;
    }

    @Override
    public String getCfPath() {
        return cfPath;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}
