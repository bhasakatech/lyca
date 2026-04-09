package com.adobe.aem.lyca.core.beans;

import lombok.Getter;

@Getter
public class BlogData {

    private String blogTitle;
    private String blogDescription;
    private String authorName;
    private String blogImage;
    private String publishDate;
    private String ctaLabel;
    private String ctaLink;
    private String fragmentPath;

    public BlogData() {}

    public void setBlogTitle(String blogTitle) { this.blogTitle = blogTitle; }

    public void setBlogDescription(String blogDescription) { this.blogDescription = blogDescription; }

    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public void setBlogImage(String blogImage) { this.blogImage = blogImage; }

    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    public void setCtaLabel(String ctaLabel) { this.ctaLabel = ctaLabel; }

    public void setCtaLink(String ctaLink) { this.ctaLink = ctaLink; }

    public void setFragmentPath(String fragmentPath) { this.fragmentPath = fragmentPath; }
}