package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.inject.Named;
import java.util.List;

@Model(
    adaptables = {Resource.class ,SlingHttpServletRequest.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BlogListModel {

    @ValueMapValue
    private String heading;

    @ChildResource(name = "blogs")
    private List<BlogItem> blogs;

    public String getHeading() {
        return heading;
    }

    public List<BlogItem> getBlogs() {
        return blogs;
    }

    // Inner class for each blog card
    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class BlogItem {

        @ValueMapValue
        @Named("blogImage")
        private String blogImage;

        @ValueMapValue
        @Named("blogTitle")
        private String blogTitle;

        @ValueMapValue
        @Named("blogDate")
        private String blogDate;

        @ValueMapValue
        @Named("blogAuthor")
        private String blogAuthor;

        public String getBlogImage() {
            return blogImage;
        }

        public String getBlogTitle() {
            return blogTitle;
        }

        public String getBlogDate() {
            return blogDate;
        }   
    }
}
