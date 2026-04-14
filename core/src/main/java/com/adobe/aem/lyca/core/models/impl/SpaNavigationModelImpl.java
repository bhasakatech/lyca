package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.SpaNavigationModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.NavigationItem;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.wcm.core.components.models.Navigation;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import java.util.List;

/**
 * Sling Model implementation for SPA Navigation component.
 *
 * <p>
 * This model extends the functionality of the Core Component Navigation
 * ({@link Navigation}) and adds custom properties like logo and CTA button.
 * </p>
 *
 * <p>
 * It adapts from {@link SlingHttpServletRequest} and supports JSON export
 * via {@link ComponentExporter} for SPA (React) integration.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Fetch navigation items from Core Component</li>
 *     <li>Limit navigation items to a maximum of 4</li>
 *     <li>Expose logo, button label, and link</li>
 * </ul>
 */
@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {SpaNavigationModel.class, ComponentExporter.class,Navigation.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = SpaNavigationModelImpl.RESOURCE_TYPE
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = {ExporterConstants.SLING_MODEL_EXTENSION}
)
public class SpaNavigationModelImpl implements SpaNavigationModel{

    /**
     * Resource type of the navigation component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/navigation";

    /**
     * Logo path used in the navigation bar.
     */
    @ValueMapValue
    private String navLogo;

    /**
     * Core Navigation model injected via resource super type.
     * Used to retrieve navigation items.
     */
    @Self
    @Via(type = ResourceSuperType.class)
    private Navigation navigation;

    /**
     * Label for the CTA button in the navigation.
     */
    @ValueMapValue
    private String buttonLabel;

    /**
     * Link for the CTA button in the navigation.
     */
    @ValueMapValue
    private String buttonLink;

    /**
     * Returns the navigation logo path.
     */
    @Override
    public String getNavLogo() {
        return navLogo;
    }

    /**
     * Returns the list of navigation items.
     *
     * <p>
     * Behavior:
     * <ul>
     *     <li>Returns null if navigation or items are unavailable</li>
     *     <li>Limits the number of items to a maximum of 4</li>
     * </ul>
     * </p>
     */
    @Override
    public List<NavigationItem> getItems() {
        if (navigation == null || navigation.getItems() == null) {
            return null;
        }
        List<NavigationItem> items = navigation.getItems(); // Return max 4 items
        return items.size() > 4 ? items.subList(0, 4) : items;
    }

    /**
     * Returns the CTA button label.
     */
    @Override
    public String getButtonLabel() {
        return buttonLabel;
    }

    /**
     * Returns the CTA button link.
     */
    @Override
    public String getButtonLink() {
        return buttonLink;
    }

    /**
     * Returns the exported resource type for Sling Model Exporter.
     */
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}