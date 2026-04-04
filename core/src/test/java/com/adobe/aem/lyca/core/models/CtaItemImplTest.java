package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class CtaItemImplTest {

    private final AemContext context = new AemContext();

    private Resource resourceWithValues;
    private Resource resourceEmpty;

    @BeforeEach
    void setUp() {
        // Load the single JSON file into /content
        context.load().json("/ctaItem.json", "/content");

        // Fetch resources
        resourceWithValues = context.resourceResolver().getResource("/content/ctaItem1");
        resourceEmpty = context.resourceResolver().getResource("/content/ctaItemEmpty");
    }

    @Test
    void testGettersWithValues() {
        CtaItem ctaItem = resourceWithValues.adaptTo(CtaItem.class);
        assertNotNull(ctaItem);

        // Test all fields
        assertEquals("sample-icon.png", ctaItem.getIcon());
        assertEquals("Click Me", ctaItem.getLabel());
        assertEquals("/content/page1", ctaItem.getLink());
    }

    @Test
    void testGettersWithNullValues() {
        CtaItem ctaItem = resourceEmpty.adaptTo(CtaItem.class);
        assertNotNull(ctaItem);
        // All fields are optional, should return null
        assertNull(ctaItem.getIcon());
        assertNull(ctaItem.getLabel());
        assertNull(ctaItem.getLink());
    }

    @Test
    void testAdaptToSlingHttpServletRequest() {
        // Create a mock request for completeness
        context.currentResource(resourceWithValues);
        CtaItem ctaItem = context.request().adaptTo(CtaItem.class);
        assertNotNull(ctaItem);

        assertEquals("sample-icon.png", ctaItem.getIcon());
        assertEquals("Click Me", ctaItem.getLabel());
        assertEquals("/content/page1", ctaItem.getLink());
    }
}