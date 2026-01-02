package com.lyca.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

    // Logos
    @ValueMapValue
    private String logoImage;

    @ValueMapValue
    private String logoMobileImage;

    // Menu
    @ValueMapValue
    private String menuImage;

    @ValueMapValue
    private String menuMobileImage;

    @ChildResource(name = "linkLabel")  
    private List<MenuItems> linkLabel;

    // Quick Recharge
    @ValueMapValue
    private String quickRechargeLabel;

    @ValueMapValue
    private String quickRechargeUrl;

    // Profile
    @ValueMapValue
    private String profileImage;

    @ValueMapValue
    private String profileDropdownImage;

    // Cart
    @ValueMapValue
    private String cartImage;

    // Country
    @ValueMapValue
    private String countryLabel;

    @ValueMapValue
    private String countryImage;

    @ValueMapValue
    private String countryDropdownImage;

    // --- Getters ---
    public String getLogoImage() {
        return logoImage;
    }

    public String getLogoMobileImage() {
        return logoMobileImage;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public String getMenuMobileImage() {
        return menuMobileImage;
    }

    public List<MenuItems> getLinkLabel() {
        return linkLabel;
    }

    public String getQuickRechargeLabel() {
        return quickRechargeLabel;
    }

    public String getQuickRechargeUrl() {
        return quickRechargeUrl;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getProfileDropdownImage() {
        return profileDropdownImage;
    }

    public String getCartImage() {
        return cartImage;
    }

    public String getCountryLabel() {
        return countryLabel;
    }

    public String getCountryImage() {
        return countryImage;
    }

    public String getCountryDropdownImage() {
        return countryDropdownImage;
    }

}
