package com.adobe.aem.lyca.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * Sling Model implementation for the Lyca Feature Component.
 *
 * <p>
 * This model adapts from both {@link Resource} and {@link SlingHttpServletRequest}
 * and exports component data as JSON using the Sling Model Exporter.
 * It represents the Lyca Feature Component used in the AEM SPA React application.
 * </p>
 *
 * <p>
 * The model retrieves:
 * </p>
 * <ul>
 *     <li>Main heading of the feature component</li>
 *     <li>List of feature card items configured under child resource "lycaFeatureCards"</li>
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

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaFeatureComponent.class, ComponentExporter.class},
        resourceType = LycaFeatureComponentImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LycaFeatureComponentImpl implements LycaFeatureComponent {

    /**
     * Resource type for the Lyca Feature Component.
     */

    public static final String RESOURCE_TYPE="lyca-spa-react/components/lycafeaturecomponent";


    /**
     * Main heading text for the Lyca Feature Component.
     */
    @ValueMapValue
    private String lycaFeatureComponentMainHeading;

    /**
     * List of feature card items authored under the child resource "lycaFeatureCards".
     */
    @ChildResource(name = "lycaFeatureCards")
    private List<FeatureItem> items;

    /**
     * Returns the main heading of the feature component.
     *
     * @return main heading text
     */
    @Override
    public String getLycaFeatureComponentMainHeading() {
        return lycaFeatureComponentMainHeading;
    }


    /**
     * Returns the list of feature card items.
     *
     * @return list of feature items
     */
    @Override
    public List<FeatureItem> getItems() {
        return items;
    }


    /**
     * Returns the exported resource type of this component.
     *
     * @return exported resource type string
     */
    @Override
    public String getExportedType() {
        return LycaFeatureComponentImpl.RESOURCE_TYPE;
    }

}