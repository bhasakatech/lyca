package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * Unit test class for {@link FooterLinkImpl}.
 * <p>
 * This class verifies the functionality of the FooterLink Sling Model,
 * ensuring that navigation label and URL properties are correctly
 * injected from the AEM resource.
 * </p>
 * <p>
 * It uses {@link AemContext} to load mock JSON data and simulate
 * the AEM repository structure.
 * </p>
 * @author Jaya Chandra Reddy
 */
@ExtendWith(AemContextExtension.class)
class FooterLinkImplTest {
    /** AEM mock context */
    private final AemContext context = new AemContext();
    /** Model under test */
    private FooterLink footerLink;
    /**
     * Initializes the test setup by loading mock data
     * and adapting the resource to {@link FooterLink}.
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(FooterLinkImpl.class);
        context.load().json("/footer.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/footer/footerLinks/item0/links/item0");
        footerLink = resource.adaptTo(FooterLink.class);
    }
    /**
     * Verifies that the model is successfully created.
     */
    @Test
    void testModelNotNull() {
        assertNotNull(footerLink);
    }
    /**
     * Tests whether the navigation label is correctly retrieved.
     */
    @Test
    void testGetNavigationLabel() {
        assertEquals("Home", footerLink.getNavigationLabel());
    }
    /**
     * Tests whether the navigation URL is correctly retrieved.
     */
    @Test
    void testGetNavigationURL() {
        assertEquals("#", footerLink.getNavigationURL());
    }
}