package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaJoinModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link LycaJoinModelImpl}.
 *
 * <p>
 * This test validates the Sling Model mapping for the Lyca Join component.
 * It ensures that AEM authored content is correctly mapped to the model
 * and exported properly for SPA (React) consumption.
 * </p>
 *
 * <p>
 * It uses {@link AemContext} to simulate AEM environment and JSON content
 * loaded from test resources.
 * </p>
 *
 * <p>
 * Covered validations:
 * <ul>
 *     <li>Basic field mapping (title, description, heading, etc.)</li>
 *     <li>CTA items multifield mapping</li>
 *     <li>CTA buttons multifield mapping</li>
 *     <li>Exported resource type validation</li>
 * </ul>
 * </p>
 */
@ExtendWith(AemContextExtension.class)
class LycaJoinModelImplTest {

    /**
     * AEM testing context used to simulate JCR and Sling environment.
     */
    private final AemContext context = new AemContext();

    /**
     * Sling Model instance under test.
     */
    private LycaJoinModel model;

    /**
     * Initializes test setup before each test execution.
     *
     * <p>
     * Registers Sling Model, loads test JSON data, sets current resource,
     * and adapts request to the model.
     * </p>
     */
    @BeforeEach
    void setUp() {

        context.addModelsForClasses(LycaJoinModelImpl.class);
        context.load().json("/joinLyca.json", "/content");
        context.currentResource("/content/joinLyca");
        model = context.request().adaptTo(LycaJoinModel.class);
        assertNotNull(model, "Model should not be null");
    }

    /**
     * Tests mapping of basic scalar fields from AEM dialog/content.
     *
     * <p>
     * Validates title, description, heading, placeholder,
     * submit text, submit link, and promotion text.
     * </p>
     */
    @Test
    void testBasicFields() {
        assertEquals("Join Lyca", model.getTitle());
        assertEquals("Join description", model.getDescription());
        assertEquals("Main Heading", model.getHeading());
        assertEquals("Enter number", model.getPlaceholder());
        assertEquals("Submit", model.getSubmitText());
        assertEquals("/submit-link", model.getSubmitLink());
        assertEquals("Promo Text", model.getPromotionText());
    }

    /**
     * Tests CTA items multifield mapping.
     *
     * <p>
     * Ensures CTA items list is not null and contains expected number of items.
     * </p>
     */
    @Test
    void testCtaItems() {
        assertNotNull(model.getCtaItems());
        assertEquals(2, model.getCtaItems().size());
    }

    /**
     * Tests CTA buttons multifield mapping.
     *
     * <p>
     * Ensures CTA buttons list is properly mapped from AEM dialog.
     * </p>
     */
    @Test
    void testCtaButtons() {
        assertNotNull(model.getCtas());
        assertEquals(2, model.getCtas().size());
    }

    /**
     * Tests exported resource type for SPA JSON export.
     *
     * <p>
     * Validates that Sling Model exports correct resource type
     * for frontend mapping.
     * </p>
     */
    @Test
    void testExportedType() {
        assertEquals(LycaJoinModelImpl.RESOURCE_TYPE, model.getExportedType());
    }
}