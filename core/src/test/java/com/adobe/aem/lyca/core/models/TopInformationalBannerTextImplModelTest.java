package com.adobe.aem.lyca.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * Unit test class for {@link TopInformationalBannerTextImplModel}.
 *
 * <p>
 * This test class validates the behavior of the
 * {@link TopInformationalBannerTextImplModel} Sling Model using AEM Mock Context.
 * It ensures that the model correctly adapts resource data and returns
 * expected values for both populated and empty banner text scenarios.
 * </p>
 *
 * <p>
 * Test coverage includes:
 * </p>
 * <ul>
 *     <li>Validation of banner text retrieval when data exists</li>
 *     <li>Validation of null handling when text is missing</li>
 *     <li>Verification of exported resource type</li>
 * </ul>
 *
 * <p>
 * Uses:
 * </p>
 * <ul>
 *     <li>{@link AemContext} for mock AEM resource setup</li>
 *     <li>{@link AemContextExtension} for JUnit 5 AEM integration</li>
 *     <li>{@link MockitoExtension} for Mockito support</li>
 * </ul>
 *
 * @author
 * Adobe
 */

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class TopInformationalBannerTextImplModelTest {

    /**
     * Mock AEM context used for Sling Model testing.
     */
    private final AemContext context = new AemContext();

    /**
     * Initializes mock resources before each test execution.
     *
     * <p>
     * Registers Sling Model classes and creates:
     * </p>
     * <ul>
     *     <li>A populated banner resource with text content</li>
     *     <li>An empty banner resource without text content</li>
     * </ul>
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(TopInformationalBannerTextImplModel.class);

        //  Create data manually instead of JSON
        context.create().resource("/content/topBanner",
                "sling:resourceType", TopInformationalBannerTextImplModel.RESOURCE_TYPE,
                "text", "<p>Hello from test</p>");

        context.create().resource("/content/topBannerEmpty",
                "sling:resourceType", TopInformationalBannerTextImplModel.RESOURCE_TYPE);
    }

    /**
     * Tests retrieval of banner text when text content is available.
     *
     * <p>
     * Verifies that the model correctly returns authored text.
     * </p>
     */
    @Test
    void testGetText() {
        Resource resource = context.resourceResolver().getResource("/content/topBanner");

        TopInformationalBannerTextImplModel model =
                resource.adaptTo(TopInformationalBannerTextImplModel.class);

        assertEquals("<p>Hello from test</p>", model.getText());
    }

    /**
     * Tests retrieval of banner text when no text content exists.
     *
     * <p>
     * Verifies that null is returned when banner text is missing.
     * </p>
     */
    @Test
    void testGetText_null() {
        Resource resource = context.resourceResolver().getResource("/content/topBannerEmpty");

        TopInformationalBannerTextImplModel model =
                resource.adaptTo(TopInformationalBannerTextImplModel.class);

        assertNull(model.getText());
    }

    /**
     * Tests the exported resource type of the model.
     *
     * <p>
     * Verifies that the exported type matches the expected component resource type.
     * </p>
     */
    @Test
    void testExportedType() {
        Resource resource = context.resourceResolver().getResource("/content/topBanner");

        TopInformationalBannerTextImplModel model =
                resource.adaptTo(TopInformationalBannerTextImplModel.class);

        assertEquals(
                TopInformationalBannerTextImplModel.RESOURCE_TYPE,
                model.getExportedType()
        );
    }
}