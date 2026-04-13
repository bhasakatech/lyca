package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.beans.BlogData;
import com.adobe.aem.lyca.core.services.BlogService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class,MockitoExtension.class})
class RecentBlogModelTest {

    private final AemContext context = new AemContext();

    private BlogService blogService;

    @BeforeEach
    void setUp() {

        blogService = Mockito.mock(BlogService.class);
        context.registerService(BlogService.class, blogService);

        context.load().json("/recentBlog.json", "/content/test");

        context.addModelsForClasses(RecentBlogModel.class);
    }

    @Test
    void testInitAndGetters() {

        BlogData blog1 = new BlogData();
        BlogData blog2 = new BlogData();
        List<BlogData> mockBlogs = Arrays.asList(blog1, blog2);

        when(blogService.getBlogs("/content/dam/blogs")).thenReturn(mockBlogs);

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertNotNull(model);
        assertEquals("Recent Blogs", model.getHeadingTitle());
        assertEquals(mockBlogs, model.getBlogs());
    }
    @Test
    void testGetExportedType() {

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertEquals(RecentBlogModel.RESOURCE_TYPE, model.getExportedType());
    }
    @Test
    void testNullServiceResponse() {

        when(blogService.getBlogs("/content/dam/blogs")).thenReturn(null);

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertNull(model.getBlogs());
    }
    @Test
    void testEmptyListResponse() {

        when(blogService.getBlogs("/content/dam/blogs")).thenReturn(List.of());

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertNotNull(model.getBlogs());
        assertTrue(model.getBlogs().isEmpty());
    }
}