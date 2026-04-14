package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.adobe.aem.lyca.core.models.impl.FeatureItem;
import com.adobe.aem.lyca.core.models.impl.LycaFeatureComponentImpl;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.SlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test class for {@link LycaFeatureComponentImpl}.
 *
 * <p>
 * This test class validates the behavior of the
 * {@link LycaFeatureComponentImpl} Sling Model using AEM Mock Context.
 * It ensures that the model correctly adapts from resource data
 * and returns expected values for both populated and empty scenarios.
 * </p>
 *
 * <p>
 * Test coverage includes:
 * </p>
 * <ul>
 *     <li>Loading and validating complete component data</li>
 *     <li>Handling empty or missing component data gracefully</li>
 *     <li>Verifying exported resource type</li>
 * </ul>
 *
 * <p>
 * Uses:
 * </p>
 * <ul>
 *     <li>{@link AemContext} for mock AEM resource setup</li>
 *     <li>{@link AemContextExtension} for AEM JUnit 5 integration</li>
 *     <li>{@link MockitoExtension} for Mockito support</li>
 * </ul>
 *
 * @author
 * Adobe
 */

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaFeatureComponentImplTest {


    /**
     * Mock AEM context used for resource and model testing.
     */
    private final AemContext context = new AemContext();


    /**
     * Model instance under test.
     */
    private LycaFeatureComponentImpl model;

    /**
     * Initializes the Sling Model package before each test.
     */
    @BeforeEach
    void setUp() {
        context.addModelsForPackage("com.adobe.aem.lyca.core.models");
    }


    /**
     * Tests the model with complete JSON data.
     *
     * <p>
     * Verifies:
     * </p>
     * <ul>
     *     <li>Resource loads successfully</li>
     *     <li>Model adapts correctly</li>
     *     <li>Main heading is populated</li>
     *     <li>Feature items list contains expected values</li>
     *     <li>Exported type matches resource type</li>
     * </ul>
     */
    @Test
    void testFullData() {
        context.load().json("/lyca-full.json", "/content/test");

        Resource resource = context.resourceResolver().getResource("/content/test");
        assertNotNull(resource);

        LycaFeatureComponentImpl model = resource.adaptTo(LycaFeatureComponentImpl.class);
        assertNotNull(model);

        // Heading
        assertEquals("Main Heading", model.getLycaFeatureComponentMainHeading());

        // Items
        List<FeatureItem> items = model.getItems();
        assertNotNull(items);
        assertEquals(2, items.size());

        assertEquals("icon1.png", items.get(0).getIcon());
        assertEquals("Feature 1", items.get(0).getFeatureHeading());

        // Exported Type
        assertEquals(LycaFeatureComponentImpl.RESOURCE_TYPE, model.getExportedType());
    }

    /**
     * Tests the model with empty JSON data.
     *
     * <p>
     * Verifies:
     * </p>
     * <ul>
     *     <li>Resource loads successfully</li>
     *     <li>Model adapts correctly</li>
     *     <li>Missing heading returns null</li>
     *     <li>Missing items return null</li>
     *     <li>Exported type remains valid</li>
     * </ul>
     */
    @Test
    void testEmptyData() {
        context.load().json("/lyca-empty.json", "/content/empty");

        Resource resource = context.resourceResolver().getResource("/content/empty");
        assertNotNull(resource);

        LycaFeatureComponentImpl model = resource.adaptTo(LycaFeatureComponentImpl.class);
        assertNotNull(model);

        assertNull(model.getLycaFeatureComponentMainHeading());
        assertNull(model.getItems());

        assertEquals(LycaFeatureComponentImpl.RESOURCE_TYPE, model.getExportedType());
    }


}