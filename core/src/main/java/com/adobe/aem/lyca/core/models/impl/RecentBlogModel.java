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

/**
 * Sling Model for the Recent Blogs component.
 *
 * <p>
 * This model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and exports data as JSON using {@link ComponentExporter} for SPA (React) rendering.
 * </p>
 *
 * <p>
 * The model integrates with {@link BlogService} to fetch blog data from
 * AEM Content Fragments using the configured content fragment path (cfPath).
 * </p>
 *
 * <p>
 * The exported data includes:
 * </p>
 * <ul>
 *     <li>Heading title for the component</li>
 *     <li>List of blog data (title, description, author, image, etc.)</li>
 * </ul>
 *
 * @author Bhaskar
 * @since 1.0
 */
@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {ComponentExporter.class},
        resourceType = RecentBlogModel.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class RecentBlogModel implements ComponentExporter {

    /**
     * Resource type for the Recent Blogs component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/recentBlogs";

    /**
     * OSGi service used to fetch blog data from Content Fragments.
     */
    @OSGiService
    private BlogService blogService;

    /**
     * Heading title displayed in the component.
     */
    @Getter
    @ValueMapValue
    private String headingTitle;

    /**
     * Content Fragment path configured in the component dialog.
     * Used to retrieve blog data via BlogService.
     */
    @ValueMapValue
    private String cfPath;

    /**
     * List of blogs fetched from the service.
     */
    @Getter
    private List<BlogData> blogs;

    /**
     * Initializes the model after all injections are complete.
     *
     * <p>
     * Fetches blog data from the {@link BlogService} using the configured
     * content fragment path (cfPath).
     * </p>
     */
    @PostConstruct
    protected void init() {
        blogs = blogService.getBlogs(cfPath);
    }

    /**
     * Returns the resource type of the component for JSON export.
     *
     * @return resource type string
     */
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}