package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroSlide {
    @ValueMapValue
    private String preTitle;
    @ValueMapValue
    private String title;
    @ValueMapValue
    private String subtitle;
    @ValueMapValue
    private String price;
    @ValueMapValue
    private String duration;
    @ValueMapValue
    private String ctaText;
    @ValueMapValue
    private String ctaLink;
    @ValueMapValue
    private String bgImage;
    @ValueMapValue
    private String bgColor;



    public String getPreTitle() {
        return preTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getCtaText() {
        return ctaText;
    }

    public String getCtaLink() {
        return ctaLink;
    }

    public String getBgImage() {
        return bgImage;
    }

    public String getBgColor() {
        return bgColor;
    }
}
