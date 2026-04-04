package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.CountryModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = CountryModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CountryModelImpl implements CountryModel {

    @ValueMapValue
    private String countryLogo;

    @ValueMapValue
    private String logoLink;

    @ValueMapValue
    private String countryName;

    @Override
    public String getCountryLogo() {
        return countryLogo;
    }

    @Override
    public String getLogoLink() {
        return logoLink;
    }

    @Override
    public String getCountryName() {
        return countryName;
    }
}