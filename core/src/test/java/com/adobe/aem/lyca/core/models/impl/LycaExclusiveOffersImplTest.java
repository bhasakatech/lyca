package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaExclusiveOffers;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link LycaExclusiveOffersImpl}.
 * <p>
 * This class validates the behavior of the Lyca Exclusive Offers Sling Model,
 * including property injection from AEM resources and JSON export functionality.
 * </p>
 * <p>
 * The test uses {@link AemContext} to simulate an AEM environment by loading
 * mock JSON content and adapting it into the model.
 * </p>
 * @author Jaya Chandra Reddy
 */
@ExtendWith(AemContextExtension.class)
class LycaExclusiveOffersImplTest {
    /** AEM mock context used for testing */
    private final AemContext context = new AemContext();
    /** Model instance under test */
    private LycaExclusiveOffers model;
    /**
     * Initializes the test setup.
     * <p>
     * Loads mock JSON data into the AEM context and adapts the resource
     * to the {@link LycaExclusiveOffers} model.
     * </p>
     */
    @BeforeEach
    void init() {
        context.addModelsForClasses(LycaExclusiveOffersImpl.class);
        context.load().json("/lycaexclusiveoffers.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/lycaexclusiveoffers");
        assertNotNull(resource);
        model = resource.adaptTo(LycaExclusiveOffers.class);
    }
    /**
     * Verifies that the model is successfully created.
     */
    @Test
    void testModelNotNull() {
        assertNotNull(model);
    }
    /**
     * Tests whether the heading value is correctly retrieved.
     */
    @Test
    void testHeading() {
        assertEquals("Sign up to get exclusive offers", model.getHeading());
    }
    /**
     * Tests whether the input placeholder text is correctly retrieved.
     */
    @Test
    void testInputPlaceholderText() {
        assertEquals("Enter your email address", model.getInputPlaceholderText());
    }
    /**
     * Tests whether the CTA label is correctly retrieved.
     */
    @Test
    void testCtaLabel() {
        assertEquals("Join our offers club", model.getCtaLabel());
    }
    /**
     * Tests whether the CTA link is correctly retrieved.
     */
    @Test
    void testCtaLink() {
        assertEquals("#", model.getCtaLink());
    }
    /**
     * Tests whether the description contains expected content.
     */
    @Test
    void testDescription() {
        assertTrue(model.getDescription().contains("relevant offers"));
    }
    /**
     * Tests whether the exported resource type is correct.
     */
    @Test
    void testExportedType() {
        assertEquals(
                "lyca-spa-react/components/lycaexclusiveoffers",
                model.getExportedType()
        );
    }
}
