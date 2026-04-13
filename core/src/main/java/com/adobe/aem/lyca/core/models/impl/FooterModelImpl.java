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

/**
 * Implementation class for the {@link FooterModel}.
 * <p>
 * This Sling Model adapts from {@link SlingHttpServletRequest}
 * and {@link Resource} and provides the complete
 * footer data structure.
 * </p>
 * <p>
 * The model includes logo, description, copyright,
 * and a structured list of footer columns with links.
 * </p>
 * <p>
 * Child resources are injected using
 * {@link ChildResource},
 * and properties are injected using
 * {@link ValueMapValue}.
 * </p>
 * <p>
 * This model supports JSON export via
 * {@link ComponentExporter}.
 * </p>
 * @author Jaya Chandra Reddy
 */
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
    /** Resource type for the Footer component. */
    public static final String RESOURCE_PATH = "lyca-spa-react/components/footer";

    /** Lyca logo path */
    @ValueMapValue
    private String lycaLogo;

    /** Footer description */
    @ValueMapValue
    private String lycaDescription;

    /** Copyright text */
    @ValueMapValue
    private String copyright;

    /** List of footer columns */
    @ChildResource(name = "footerLinks")
    private List<FooterColumn> footerLinks;

    /**
     * Returns the exported resource type.
     * @return the resource type
     */
    @Override
    public String getExportedType() {
        return RESOURCE_PATH;
    }

    /** {@inheritDoc} */
    @Override
    public String getLycaLogo() {
        return lycaLogo;
    }

    /** {@inheritDoc} */
    @Override
    public String getLycaDescription() {
        return lycaDescription;
    }

    /** {@inheritDoc} */
    @Override
    public String getCopyright() {
        return copyright;
    }

    /** {@inheritDoc} */
    @Override
    public List<FooterColumn> getFooterLinks() {
        return footerLinks;
    }
}
