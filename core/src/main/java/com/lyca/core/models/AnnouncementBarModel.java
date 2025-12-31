package com.lyca.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AnnouncementBarModel {

    @ValueMapValue
    private String announcementText;

    @ValueMapValue
    private String announcementImage;

    @ValueMapValue
    private String announcementLink;

    public String getAnnouncementText() {
        return announcementText;
    }

    public String getAnnouncementImage() {
        return announcementImage;
    }

    public String getAnnouncementLink() {
        return announcementLink;
    }
}
