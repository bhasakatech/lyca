package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

/**
 * Unit test class for {@link LycaFAQComponentModelImpl}.
 *
 * <p>This test class validates the functionality of the Lyca FAQ Component Sling Model
 * using AEM Mocks ({@link AemContext}). It ensures that the model correctly adapts
 * from a resource, retrieves authored properties, and maps multifield data.</p>
 *
 * <p>The test data is loaded from a JSON file located in the test resources
 * (e.g., {@code /lyca-faq-component.json}), simulating the JCR structure.</p>
 *
 * <p>Test coverage includes:</p>
 * <ul>
 *     <li>Model adaptation from resource</li>
 *     <li>Validation of exported resource type</li>
 *     <li>Retrieval of main heading</li>
 *     <li>Mapping of FAQ multifield items</li>
 *     <li>Validation of individual FAQ entries (question and answer)</li>
 *     <li>Handling of empty or missing data scenarios</li>
 * </ul>
 */

@ExtendWith(AemContextExtension.class)
class LycaFAQComponentModelImplTest {

    /**
     * AEM mock context used to simulate repository, resources, and Sling Models.
     */
    private final AemContext context = new AemContext();

    /**
     * Instance of the model under test.
     */
    private LycaFAQComponentModelImpl model;

    /**
     * Sets up the test environment before each test.
     *
     * <p>This includes:</p>
     * <ul>
     *     <li>Registering Sling Models</li>
     *     <li>Loading JSON test content into mock repository</li>
     *     <li>Adapting the resource to the model</li>
     * </ul>
     */
    @BeforeEach
    void setUp() {

        context.addModelsForClasses(
                LycaFAQComponentModelImpl.class,
                LycaFAQs.class
        );

        context.load().json("/lyca-faq-component.json", "/content/faq");

        Resource resource = context.resourceResolver().getResource("/content/faq");

        model = resource.adaptTo(LycaFAQComponentModelImpl.class);
    }


    /**
     * Verifies that the model is successfully adapted from the resource.
     */
    @Test
    void testModelNotNull() {
        assertNotNull(model);
    }

    /**
     * Verifies that the exported resource type matches the expected value.
     */
    @Test
    void testExportedType() {
        assertEquals(
                LycaFAQComponentModelImpl.RESOURCE_TYPE,
                model.getExportedType()
        );
    }

    /**
     * Verifies that the main heading is correctly retrieved from the JSON data.
     */
    @Test
    void testMainHeading() {
        assertEquals("FAQ Main Heading", model.getMainHeading());
    }

    /**
     * Verifies that the FAQ list is not null and contains the expected number of items.
     */
    @Test
    void testFAQsList() {
        List<LycaFAQs> items = model.getFAQs();

        assertNotNull(items);
        assertEquals(2, items.size());
    }

    /**
     * Verifies the values of the first FAQ item.
     */
    @Test
    void testFirstFAQ() {
        List<LycaFAQs> items = model.getFAQs();

        assertEquals("What is Lyca?", items.get(0).getQuestion());
        assertEquals("<p>Lyca is a telecom company</p>", items.get(0).getAns());
    }

    /**
     * Verifies the values of the second FAQ item.
     */
    @Test
    void testSecondFAQ() {
        List<LycaFAQs> items = model.getFAQs();

        assertEquals("How to recharge?", items.get(1).getQuestion());
        assertEquals("<p>You can recharge online</p>", items.get(1).getAns());
    }

    /**
     * Verifies model behavior when no data is authored.
     *
     * <p>This test ensures that the model handles empty resources gracefully
     * by returning null values instead of throwing exceptions.</p>
     */
    @Test
    void testEmptyCase() {
        // create empty resource
        Resource emptyRes = context.create().resource(
                "/content/empty",
                "sling:resourceType", LycaFAQComponentModelImpl.RESOURCE_TYPE
        );

        LycaFAQComponentModelImpl emptyModel =
                emptyRes.adaptTo(LycaFAQComponentModelImpl.class);

        assertNotNull(emptyModel);
        assertNull(emptyModel.getMainHeading());
        assertNull(emptyModel.getFAQs());
    }
}