package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.api.SlingHttpServletRequest;

import java.util.List;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DownloadAppModel {

    @ValueMapValue
    private String mainImage;

    @ValueMapValue
    private String mobileImage;

    @ValueMapValue
    private String heading1;

    @ValueMapValue
    private String heading2;

    @ChildResource(name = "features")
    private List<Feature> features;

    @ChildResource(name = "appStoreLinks")
    private List<AppStoreLink> appStoreLinks;

    public String getMainImage() {
        return mainImage;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public String getHeading1() {
        return heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public List<AppStoreLink> getAppStoreLinks() {
        return appStoreLinks;
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Feature {

        @ValueMapValue
        private String featureIcon;

        @ValueMapValue
        private String featureText;

        public String getFeatureIcon() {
            return featureIcon;
        }

        public String getFeatureText() {
            return featureText;
        }
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class AppStoreLink {

        @ValueMapValue
        private String storeLink;

        @ValueMapValue
        private String storeIcon;

        public String getStoreLink() {
            return storeLink;
        }

        public String getStoreIcon() {
            return storeIcon;
        }
    }
}
