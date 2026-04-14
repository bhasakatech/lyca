package com.adobe.aem.lyca.core.models;

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
 * Sling Model implementation for the Lyca Help Component.
 *
 * <p>
 * This model adapts from both {@link Resource} and
 * {@link SlingHttpServletRequest}, and exports component data as JSON
 * using the Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The Lyca Help Component provides:
 * </p>
 * <ul>
 *     <li>A main heading for the help section</li>
 *     <li>A list of helping icon items authored as child resources</li>
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

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaHelpComponentModel.class, ComponentExporter.class},
        resourceType = LycaHelpComponentModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
          extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class LycaHelpComponentModelImpl implements LycaHelpComponentModel {

    /**
     * Resource type for the Lyca Help Component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycahelpcomponent";

    /**
     * Heading text of the help component.
     */
    @ValueMapValue
    private String heading;


    /**
     * List of helping icon items configured under child resources.
     */
    @ChildResource
    private List<HelperComponentsModel> helpingIcons;

    /**
     * Returns the exported resource type of this component.
     *
     * @return exported resource type string
     */
    @Override
    public String getExportedType() {
        return LycaHelpComponentModelImpl.RESOURCE_TYPE;
    }

    /**
     * Returns the heading text of the help component.
     *
     * @return heading string
     */
    @Override
    public String getHeading() {
        return heading;
    }


    /**
     * Returns the list of helping icon items.
     *
     * @return list of helper component models
     */
    @Override
    public List<HelperComponentsModel> getHelpingIcons() {
        return helpingIcons;
    }

}
