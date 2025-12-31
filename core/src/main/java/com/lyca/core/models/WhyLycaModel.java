package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.api.SlingHttpServletRequest;

import java.util.List;

@Model(
    adaptables = {Resource.class, SlingHttpServletRequest.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WhyLycaModel {

    @ValueMapValue
    private String sectionHeader;

    @ChildResource(name = "whyLycaCards")
    private List<WhyLycaCard> whyLycaCards;

    public String getSectionHeader() {
        return sectionHeader;
    }

    public List<WhyLycaCard> getWhyLycaCards() {
        return whyLycaCards;
    }

    @Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
    )
    public static class WhyLycaCard {
        
        @ValueMapValue
        private String cardImage;

        @ValueMapValue
        private String cardTitle;

        @ValueMapValue
        private String cardDescription;

        public String getCardImage() {
            return cardImage;
        }

        public String getCardTitle() {
            return cardTitle;
        }

        public String getCardDescription() {
            return cardDescription;
        }
    }
}
