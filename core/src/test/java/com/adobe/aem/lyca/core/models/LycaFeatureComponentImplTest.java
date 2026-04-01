package com.adobe.aem.lyca.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaFeatureComponentImplTest {

    private final AemContext context = new AemContext();

    private LycaFeatureComponentImpl model;

    @BeforeEach
    void setUp() {
        context.addModelsForPackage("com.adobe.aem.lyca.core.models");
    }

    // ✅ FULL DATA TEST
    @Test
    void testFullData() {
        context.load().json("/lyca-full.json", "/content/test");

        Resource resource = context.resourceResolver().getResource("/content/test");
        assertNotNull(resource);

        LycaFeatureComponentImpl model = resource.adaptTo(LycaFeatureComponentImpl.class);
        assertNotNull(model);

        // Heading
        assertEquals("Main Heading", model.getHeading());

        // Items
        List<FeatureItem> items = model.getItems();
        assertNotNull(items);
        assertEquals(2, items.size());

        assertEquals("icon1.png", items.get(0).getIcon());
        assertEquals("Feature 1", items.get(0).getFeatureHeading());

        // Exported Type
        assertEquals(LycaFeatureComponentImpl.RESOURCE_TYPE, model.getExportedType());
    }

    // ✅ EMPTY JSON (NULL BRANCH)
    @Test
    void testEmptyData() {
        context.load().json("/lyca-empty.json", "/content/empty");

        Resource resource = context.resourceResolver().getResource("/content/empty");
        assertNotNull(resource);

        LycaFeatureComponentImpl model = resource.adaptTo(LycaFeatureComponentImpl.class);
        assertNotNull(model);

        assertNull(model.getHeading());
        assertNull(model.getItems());

        assertEquals(LycaFeatureComponentImpl.RESOURCE_TYPE, model.getExportedType());
    }


}