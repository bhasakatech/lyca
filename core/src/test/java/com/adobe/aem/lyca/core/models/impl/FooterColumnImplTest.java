package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import com.adobe.aem.lyca.core.models.FooterColumn;
import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * Unit test class for {@link FooterColumnImpl}.
 * <p>
 * This class verifies the behavior of the FooterColumn Sling Model,
 * including the retrieval of column title and associated footer links.
 * </p>
 * <p>
 * It uses {@link AemContext} to simulate AEM resources and load
 * mock JSON data for testing.
 * </p>
 * @author Jaya Chandra Reddy
 */
@ExtendWith(AemContextExtension.class)
class FooterColumnImplTest {
    /** AEM mock context */
    private final AemContext context = new AemContext();
    /** Model under test */
    private FooterColumn footerColumn;
    /**
     * Initializes the test setup by registering models,
     * loading mock JSON data, and adapting the resource
     * to {@link FooterColumn}.
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(
                FooterColumnImpl.class,
                FooterLinkImpl.class
        );
        context.load().json("/footer.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/footer/footerLinks/item0");
        footerColumn = resource.adaptTo(FooterColumn.class);
    }
    /**
     * Verifies that the FooterColumn model is successfully created.
     */
    @Test
    void testModelNotNull() {
        assertNotNull(footerColumn);
    }
    /**
     * Tests whether the column title is correctly retrieved.
     */
    @Test
    void testTitle() {
        assertEquals("Join Lyca Mobile", footerColumn.getTitle());
    }
    /**
     * Tests whether the footer links are correctly retrieved
     * along with their labels and URLs.
     */
    @Test
    void testLinks() {
        List<FooterLink> links = footerColumn.getLinks();
        assertNotNull(links);
        assertEquals(4, links.size());
        assertEquals("Home", links.get(0).getNavigationLabel());
        assertEquals("#", links.get(0).getNavigationURL());
        assertEquals("Plans", links.get(1).getNavigationLabel());
        assertEquals("#", links.get(1).getNavigationURL());
    }
}