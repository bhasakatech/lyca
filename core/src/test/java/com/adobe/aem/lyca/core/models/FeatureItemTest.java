package com.adobe.aem.lyca.core.models;

import com.adobe.aem.lyca.core.models.impl.FeatureItem;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class FeatureItemTest {

    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(FeatureItem.class);
    }


    @Test
    void testAllFields() {
        context.load().json("/featureitem.json", "/content/test");

        Resource resource = context.resourceResolver().getResource("/content/test");
        assertNotNull(resource);

        FeatureItem model = resource.adaptTo(FeatureItem.class);
        assertNotNull(model);

        assertEquals("/content/dam/icon.png", model.getIcon());
        assertEquals("/home", model.getIconLink());
        assertEquals("Test Heading", model.getFeatureHeading());
        assertEquals("<p>Test Description</p>", model.getFeatureDescription());
    }
    @Test
    void testNullValues() {
        context.load().json("/empty.json", "/content/empty");

        Resource resource = context.resourceResolver().getResource("/content/empty");
        assertNotNull(resource);

        FeatureItem model = resource.adaptTo(FeatureItem.class);
        assertNotNull(model);

        assertNull(model.getIcon());
        assertNull(model.getIconLink());
        assertNull(model.getFeatureHeading());
        assertNull(model.getFeatureDescription());
    }

}