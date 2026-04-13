package com.adobe.aem.lyca.core.beans;

import lombok.Getter;

/**
 * Bean class representing Blog data mapped from AEM Content Fragments.
 *
 * <p>
 * This class acts as a data transfer object (DTO) used to store and transport
 * blog-related information retrieved from Content Fragment fields.
 * </p>
 *
 * <p>
 * It is typically populated in the service layer and consumed by Sling Models
 * for exporting data to the frontend (e.g., React components).
 * </p>
 *
 * <p>
 * Fields in this class correspond to Content Fragment elements such as
 * blogTitle, blogDescription, authorName, etc.
 * </p>
 *
 * @author Bhaskar
 * @since 1.0
 */
@Getter
public class BlogData {

    /** Title of the blog */
    private String blogTitle;

    /** Description or summary of the blog */
    private String blogDescription;

    /** Name of the blog author */
    private String authorName;

    /** Path or URL of the blog image */
    private String blogImage;

    /** Blog publish date */
    private String publishDate;

    /** Call-to-action label (e.g., "Read More") */
    private String ctaLabel;

    /** Call-to-action link for navigation */
    private String ctaLink;

    /** Content Fragment path from which the blog data is retrieved */
    private String fragmentPath;

    /**
     * Default constructor.
     */
    public BlogData() {}

    /**
     * Sets the blog title.
     *
     * @param blogTitle the title of the blog
     */
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    /**
     * Sets the blog description.
     *
     * @param blogDescription the blog description or summary
     */
    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    /**
     * Sets the author name.
     *
     * @param authorName name of the blog author
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Sets the blog image.
     *
     * @param blogImage image path or URL
     */
    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    /**
     * Sets the publish date.
     *
     * @param publishDate blog publish date
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Sets the CTA label.
     *
     * @param ctaLabel label for the call-to-action
     */
    public void setCtaLabel(String ctaLabel) {
        this.ctaLabel = ctaLabel;
    }

    /**
     * Sets the CTA link.
     *
     * @param ctaLink URL or path for the call-to-action
     */
    public void setCtaLink(String ctaLink) {
        this.ctaLink = ctaLink;
    }

    /**
     * Sets the Content Fragment path.
     *
     * @param fragmentPath path of the Content Fragment
     */
    public void setFragmentPath(String fragmentPath) {
        this.fragmentPath = fragmentPath;
    }
}