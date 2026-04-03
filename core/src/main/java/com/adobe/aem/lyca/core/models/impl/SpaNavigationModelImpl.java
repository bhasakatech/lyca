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

@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {SpaNavigationModel.class, ComponentExporter.class,Navigation.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = SpaNavigationModelImpl.RESOURCE_TYPE
)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = {ExporterConstants.SLING_MODEL_EXTENSION}
)
public class SpaNavigationModelImpl implements SpaNavigationModel{
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/content/navigation";

    @ValueMapValue
    private String navLogo;

    @Self
    @Via(type = ResourceSuperType.class)
    private Navigation navigation;

    @ValueMapValue
    private String buttonLabel;

    @ValueMapValue
    private String buttonLink;

    @Override
    public String getNavLogo() {
        return navLogo;
    }

    @Override
    public List<NavigationItem> getItems() {
        if (navigation == null || navigation.getItems() == null) {
            return null;
        }
        List<NavigationItem> items = navigation.getItems(); // Return max 4 items
        return items.size() > 4 ? items.subList(0, 4) : items;
    }

    @Override
    public String getButtonLabel() {
        return buttonLabel;
    }

    @Override
    public String getButtonLink() {
        return buttonLink;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}