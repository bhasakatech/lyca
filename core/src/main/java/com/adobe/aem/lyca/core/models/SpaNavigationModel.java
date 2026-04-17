package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.wcm.core.components.models.Navigation;
import com.adobe.cq.wcm.core.components.models.NavigationItem;

import java.util.List;

public interface SpaNavigationModel extends Navigation, ComponentExporter {
    String getNavLogo();
    List<NavigationItem> getItems();
    String getButtonLabel();
    String getButtonLink();
    String getCart();
    String getCartPageLocation();
    List<SpaNavigationLocale> getLocale();
}
