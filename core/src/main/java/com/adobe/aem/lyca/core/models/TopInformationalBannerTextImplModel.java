package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


/**
 * Sling Model implementation for the Top Informational Banner Text Component.
 *
 * <p>
 * This model adapts from both {@link SlingHttpServletRequest} and
 * {@link Resource}, and exports component data as JSON using the
 * Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The Top Informational Banner Text Component provides:
 * </p>
 * <ul>
 *     <li>Text content displayed in the top informational banner</li>
 * </ul>
 *
 * <p>
 * Resource type mapped:
 * {@value #RESOURCE_TYPE}
 * </p>
 *
 * @author
 * Adobe
 */
@Model(adaptables={SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
         resourceType=TopInformationalBannerTextImplModel.RESOURCE_TYPE,
         adapters= ComponentExporter.class)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,extensions=ExporterConstants.SLING_MODEL_EXTENSION)
public class TopInformationalBannerTextImplModel implements TopInformationalBannerText {

    /**
     * Resource type for the Top Informational Banner Text Component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/topInformationalBannerTextComponent";

    /**
     * Text content displayed in the informational banner.
     */
    @ValueMapValue
    private String text;

    /**
     * Returns the banner text content.
     *
     * @return banner text string
     */
    @Override
    public String getText() {
        return text;
    }


    /**
     * Returns the exported resource type of this component.
     *
     * @return exported resource type string
     */
    @Override
    public String getExportedType() {
        return TopInformationalBannerTextImplModel.RESOURCE_TYPE;
    }
}
