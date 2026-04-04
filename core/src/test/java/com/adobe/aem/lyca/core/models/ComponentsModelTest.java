package com.adobe.aem.lyca.core.models;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ComponentsModelTest {
    private final AemContext context = new AemContext();
    private ComponentsModel model;
    @BeforeEach
    void setUp() {
        // Load JSON into context
        context.load().json("/componentsmodel.json", "/content/test");

        Resource resource = context.resourceResolver().getResource("/content/test");
        context.currentResource(resource);

        model = resource.adaptTo(ComponentsModel.class);
    }
    @Test
    void testModelValues() {
        assertNotNull(model);

        assertEquals("/content/dam/icon.png", model.getIcon());
        assertEquals("https://example.com", model.getIconLink());
        assertEquals("Test Heading", model.getIconHeading());
    }
    @Test
    void testModelWithEmptyResource() {
        context.create().resource("/content/empty");
        Resource resource = context.resourceResolver().getResource("/content/empty");
        ComponentsModel emptyModel = resource.adaptTo(ComponentsModel.class);
        assertNotNull(emptyModel);
        assertNull(emptyModel.getIcon());
        assertNull(emptyModel.getIconLink());
        assertNull(emptyModel.getIconHeading());
    }
}