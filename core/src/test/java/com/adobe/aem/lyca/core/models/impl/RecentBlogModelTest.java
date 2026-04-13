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

/**
 * Unit test class for {@link RecentBlogModel}.
 *
 * <p>
 * This test validates the Sling Model responsible for fetching and exposing
 * recent blog data in AEM SPA components.
 * </p>
 *
 * <p>
 * It verifies integration between:
 * <ul>
 *     <li>{@link RecentBlogModel}</li>
 *     <li>{@link BlogService}</li>
 *     <li>AEM Content Repository (via AemContext)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Covered scenarios:
 * <ul>
 *     <li>Initialization and successful blog retrieval</li>
 *     <li>Exported type validation</li>
 *     <li>Null service response handling</li>
 *     <li>Empty list response handling</li>
 * </ul>
 * </p>
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class RecentBlogModelTest {

    /**
     * AEM mock context used to simulate JCR repository and Sling environment.
     */
    private final AemContext context = new AemContext();

    /**
     * Mocked BlogService used to simulate blog data fetching logic.
     */
    private BlogService blogService;

    /**
     * Initializes test environment before each test execution.
     *
     * <p>
     * Registers mocked BlogService, loads test JSON content, and
     * registers Sling Model for adaptation.
     * </p>
     */
    @BeforeEach
    void setUp() {

        blogService = Mockito.mock(BlogService.class);
        context.registerService(BlogService.class, blogService);

        context.load().json("/recentBlog.json", "/content/test");

        context.addModelsForClasses(RecentBlogModel.class);
    }

    /**
     * Tests successful initialization of RecentBlogModel and blog data mapping.
     *
     * <p>
     * Validates that:
     * <ul>
     *     <li>Heading title is correctly mapped</li>
     *     <li>BlogService returns expected data</li>
     *     <li>Blogs are correctly set in the model</li>
     * </ul>
     * </p>
     */
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

    /**
     * Tests exported type of the Sling Model.
     *
     * <p>
     * Ensures correct resource type is exposed for SPA JSON export.
     * </p>
     */
    @Test
    void testGetExportedType() {

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertEquals(RecentBlogModel.RESOURCE_TYPE, model.getExportedType());
    }

    /**
     * Tests behavior when BlogService returns null.
     *
     * <p>
     * Expected behavior: model should handle null gracefully.
     * </p>
     */
    @Test
    void testNullServiceResponse() {

        when(blogService.getBlogs("/content/dam/blogs")).thenReturn(null);

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertNull(model.getBlogs());
    }

    /**
     * Tests behavior when BlogService returns an empty list.
     *
     * <p>
     * Expected behavior: model should return an empty but non-null list.
     * </p>
     */
    @Test
    void testEmptyListResponse() {

        when(blogService.getBlogs("/content/dam/blogs")).thenReturn(List.of());

        Resource resource = context.resourceResolver().getResource("/content/test");
        RecentBlogModel model = resource.adaptTo(RecentBlogModel.class);

        assertNotNull(model.getBlogs());
        assertTrue(model.getBlogs().isEmpty());
    }
}