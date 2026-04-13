package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link PricePlanCFModel}.
 *
 * This class verifies:
 * - Successful adaptation of resource to Sling Model
 * - Correct mapping of Content Fragment (CF) properties
 *
 * Uses AEM Mock (AemContext) to simulate repository content.
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class PricePlanCFModelTest {

    /** AEM mock context to simulate repository */
    private final AemContext context = new AemContext();

    /** Model under test */
    private PricePlanCFModel model;

    /**
     * Setup method executed before each test.
     *
     * - Registers the Sling Model
     * - Loads JSON test data into mock repository
     */
    @BeforeEach
    void init() {
        context.addModelsForClasses(PricePlanCFModel.class);
        context.load().json("/priceplancf.json", "/content");
    }

    /**
     * Test to verify valid Content Fragment data mapping.
     *
     * Ensures:
     * - Resource is successfully adapted to model
     * - All fields are correctly populated
     * - Array and boolean values are properly handled
     */
    @Test
    void testValidPricePlan() {
        /** Fetch test resource */
        Resource resource = context.resourceResolver().getResource("/content/priceplan-valid");
        assertNotNull(resource);

        /** Adapt resource to Sling Model */
        model = resource.adaptTo(PricePlanCFModel.class);

        /** Validate model is created */
        assertNotNull(model);

        /** Verify field mappings */
        assertEquals("Starter", model.getPlanTitle());
        assertEquals(5.0, model.getPriceMonthly());
        assertEquals(50.0, model.getPriceYearly());
        assertEquals("5GB Monthly Plan", model.getDataLimit());

        /** Verify array field */
        assertNotNull(model.getFeatures());
        assertEquals(3, model.getFeatures().length);

        /** Verify boolean field */
        assertFalse(model.isPopular());

        /** Verify CTA fields */
        assertEquals("Choose Plan", model.getCtaLabel());
        assertEquals("/plans/starter", model.getCtaLink());
    }
}