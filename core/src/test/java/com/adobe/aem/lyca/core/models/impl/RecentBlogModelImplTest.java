package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.RecentBlogModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class RecentBlogModelImplTest {

    private final AemContext context = new AemContext();

    private RecentBlogModel model;

    @BeforeEach
    void setUp() {
        // ✅ Load JSON into context
        context.load().json("/recentblogs.json", "/content");

        // Get resource from JSON
        Resource resource = context.resourceResolver()
                .getResource("/content/recentBlogs");

        // Adapt to model
        model = resource.adaptTo(RecentBlogModel.class);
    }

    @Test
    void testModelNotNull() {
        assertNotNull(model);
    }

    @Test
    void testHeadingTitle() {
        assertEquals("Test Heading from JSON", model.getHeadingTitle());
    }

    @Test
    void testCfPath() {
        assertEquals("/content/dam/test-json", model.getCfPath());
    }

    @Test
    void testExportedType() {
        assertEquals("lyca-spa-react/components/recentBlogs",
                model.getExportedType());
    }
}