package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


/**
 * Unit test class for {@link LycaFAQs}.
 *
 * <p>This test class validates the functionality of the {@link LycaFAQs} Sling Model
 * using AEM Mocks ({@link AemContext}). It ensures that the model correctly adapts
 * from a resource and retrieves the expected values for FAQ fields.</p>
 *
 * <p>Test data is loaded from a JSON file (e.g., {@code /lyca-faq-item.json})
 * which simulates the JCR content structure.</p>
 *
 * <p>Test coverage includes:</p>
 * <ul>
 *     <li>Model adaptation from resource</li>
 *     <li>Retrieval of question field</li>
 *     <li>Retrieval of answer field</li>
 * </ul>
 */

@ExtendWith(AemContextExtension.class)
class LycaFAQsTest {

    /**
     * AEM mock context used to simulate repository and resource resolution.
     */
    private final AemContext context = new AemContext();

    /**
     * Instance of the model under test.
     */
    private LycaFAQs model;

    /**
     * Sets up the test environment before each test.
     *
     * <p>This includes loading JSON test data into the mock repository
     * and adapting the resource to the {@link LycaFAQs} model.</p>
     */
    @BeforeEach
    void setUp() {
        context.load().json("/lyca-faq-item.json", "/content/faq");

        Resource resource = context.resourceResolver().getResource("/content/faq");

        model = resource.adaptTo(LycaFAQs.class);
    }

    /**
     * Verifies that the model is successfully adapted from the resource.
     */
    @Test
    void testModelNotNull() {
        assertNotNull(model);
    }

    /**
     * Verifies that the question field is correctly retrieved from the JSON data.
     */
    @Test
    void testQuestion() {
        assertEquals("What is Lyca?", model.getQuestion());
    }

    /**
     * Verifies that the answer field is correctly retrieved from the JSON data.
     */
    @Test
    void testAnswer() {
        assertEquals("<p>Lyca is a telecom company</p>", model.getAns());
    }
}