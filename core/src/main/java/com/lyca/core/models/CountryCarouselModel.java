package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.List;

@Model(
    adaptables = {Resource.class, SlingHttpServletRequest.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CountryCarouselModel {

    @ValueMapValue
    private String heading;

    @ChildResource(name = "countries")
    private List<CountryItem> countries;

    public String getHeading() {
        return heading;
    }

    public List<CountryItem> getCountries() {
        return countries;
    }

    @Model(adaptables = Resource.class
    ,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class CountryItem {

        @ValueMapValue
        private String countryName;

        @ValueMapValue
        private String countryFlag;

        public String getCountryName() {
            return countryName;
        }

        public String getCountryFlag() {
            return countryFlag;
        }
    }
}
