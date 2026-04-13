package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.beans.BlogData;
import com.adobe.aem.lyca.core.services.BlogService;

import com.adobe.aem.lyca.core.utils.NPUtilService;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;

import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.LoginException;

import java.util.*;

@Component(service = BlogService.class)
public class BlogServiceImpl implements BlogService {

    static final Logger LOG = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Reference
    NPUtilService npUtilService;

    @Override
    public List<BlogData> getBlogs(String parentPath) {

        List<BlogData> blogList = new ArrayList<>();

        if (parentPath == null || parentPath.isEmpty()) {
            return blogList;
        }
        try (ResourceResolver resolver = npUtilService.getResourceResolver()) {

            String queryStr =
                    "SELECT * FROM [dam:Asset] AS s " +
                            "WHERE ISDESCENDANTNODE(s, '" + parentPath + "') " +
                            "AND s.[jcr:content/contentFragment] = true";

            Iterator<Resource> resources = resolver.findResources(queryStr, "JCR-SQL2");

            while (resources.hasNext()) {
                Resource resource = resources.next();

                ContentFragment fragment = resource.adaptTo(ContentFragment.class);
                if (fragment == null) continue;

                BlogData blog = new BlogData();

                blog.setBlogTitle(getElement(fragment, "blogTitle"));
                blog.setBlogDescription(getElement(fragment, "blogDescription"));
                blog.setAuthorName(getElement(fragment, "authorName"));
                blog.setBlogImage(getElement(fragment, "blogImage"));
                blog.setPublishDate(getElement(fragment, "publishDate"));
                blog.setCtaLabel(getElement(fragment, "ctaLabel"));
                blog.setCtaLink(getElement(fragment, "ctaLink"));
                blog.setFragmentPath(resource.getPath());
                blogList.add(blog);
            }
        } catch (LoginException e) {
          LOG.error("Service User Not Created || Resource Not Found", e);
        }
        return blogList;
    }
    private String getElement(ContentFragment fragment, String fieldName) {
        ContentElement element = fragment.getElement(fieldName);
        return element != null ? element.getContent() : "";
    }
}