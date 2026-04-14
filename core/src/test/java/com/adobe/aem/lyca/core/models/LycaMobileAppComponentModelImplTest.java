package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test class for {@link LycaMobileAppComponentModelImpl}.
 *
 * <p>
 * This test class verifies the functionality of the
 * {@link LycaMobileAppComponentModelImpl} Sling Model using AEM Mock Context.
 * It ensures that the model correctly adapts resource data and returns
 * expected values for both populated and empty resource scenarios.
 * </p>
 *
 * <p>
 * Test coverage includes:
 * </p>
 * <ul>
 *     <li>Validation of main heading field mapping</li>
 *     <li>Validation of mobile app feature list</li>
 *     <li>Validation of app store child resource list</li>
 *     <li>Verification of QR text and image fields</li>
 *     <li>Verification of mobile image field</li>
 *     <li>Validation of exported resource type</li>
 *     <li>Handling empty resource data safely</li>
 * </ul>
 *
 * <p>
 * Uses:
 * </p>
 * <ul>
 *     <li>{@link AemContext} for mock AEM resource setup</li>
 *     <li>{@link AemContextExtension} for JUnit 5 AEM integration</li>
 * </ul>
 *
 * @author
 * Adobe
 */
@ExtendWith(AemContextExtension.class)
class LycaMobileAppComponentModelImplTest {


    /**
     * Mock AEM context used for Sling Model testing.
     */
    private final AemContext context = new AemContext();

    /**
     * Model instance under test.
     */
    private LycaMobileAppComponentModel model;

    /**
     * Initializes test data before each test execution.
     *
     * <p>
     * Loads mock JSON content into AEM context and adapts
     * the test resource into the Sling Model.
     * </p>
     */
    @BeforeEach
    void setUp() {
        context.load().json(
                "/LycaMobileAppComponentModelImplTest.json",
                "/content"
        );

        Resource resource = context.resourceResolver().getResource("/content/test");

        model = resource.adaptTo(LycaMobileAppComponentModel.class);
    }

    /**
     * Tests the model when valid authored data is available.
     *
     * <p>
     * Verifies:
     * </p>
     * <ul>
     *     <li>Model adapts successfully</li>
     *     <li>Main heading is correctly mapped</li>
     *     <li>Mobile app features list is populated</li>
     *     <li>App store components list is populated</li>
     *     <li>QR text and images are correctly mapped</li>
     *     <li>Exported type is correct</li>
     * </ul>
     */
    @Test
    void testModelWithData() {

        assertNotNull(model);

        assertEquals("Test Heading", model.getMainHeading());

        assertNotNull(model.getMobileAppFeatures());
        assertEquals(2, model.getMobileAppFeatures().size());

        assertNotNull(model.getAppStoreComponents());
        assertEquals(2, model.getAppStoreComponents().size());

        assertEquals("Scan Me", model.getQRText());
        assertEquals("/content/dam/qr.png", model.getQRImage());
        assertEquals("/content/dam/mobile.png", model.getMobileImage());

        assertEquals(LycaMobileAppComponentModelImpl.RESOURCE_TYPE,
                model.getExportedType());
    }

    /**
     * Tests the model when resource contains no authored data.
     *
     * <p>
     * Verifies:
     * </p>
     * <ul>
     *     <li>Model adapts successfully from empty resource</li>
     *     <li>All missing fields return null</li>
     *     <li>Exported type remains valid</li>
     * </ul>
     */
    @Test
    void testModelWithEmptyData() {

        Resource resource = context.resourceResolver().getResource("/content/empty");

        LycaMobileAppComponentModel emptyModel =
                resource.adaptTo(LycaMobileAppComponentModel.class);

        assertNotNull(emptyModel);

        assertNull(emptyModel.getMainHeading());
        assertNull(emptyModel.getMobileAppFeatures());
        assertNull(emptyModel.getAppStoreComponents());
        assertNull(emptyModel.getQRText());
        assertNull(emptyModel.getQRImage());
        assertNull(emptyModel.getMobileImage());

        assertEquals(LycaMobileAppComponentModelImpl.RESOURCE_TYPE,
                emptyModel.getExportedType());
    }
}