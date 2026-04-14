package com.adobe.aem.lyca.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Unit test class for {@link LycaHelpComponentModelImpl}.
 *
 * <p>
 * This test class validates the functionality of the
 * {@link LycaHelpComponentModelImpl} Sling Model using AEM Mock Context.
 * It verifies that the model correctly adapts resource data and returns
 * expected values for both populated and empty resource scenarios.
 * </p>
 *
 * <p>
 * Test coverage includes:
 * </p>
 * <ul>
 *     <li>Validation of heading field mapping</li>
 *     <li>Validation of helping icon child resource list</li>
 *     <li>Verification of exported resource type</li>
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
class LycaHelpComponentModelImplTest {

    /**
     * Mock AEM context used for Sling Model testing.
     */
    private final AemContext context = new AemContext();

    /**
     * Model instance under test.
     */
    private LycaHelpComponentModelImpl model;


    /**
     * Sets up test environment before each test execution.
     *
     * <p>
     * Registers Sling Models, loads mock JSON content,
     * sets current resource, and adapts it into the model.
     * </p>
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(
                LycaHelpComponentModelImpl.class,
                LycaHelpComponentModel.class
        );
        context.load().json("/lycahelpcomponent.json", "/content/test");
        Resource resource = context.resourceResolver().getResource("/content/test");
        context.currentResource(resource);
        model = resource.adaptTo(LycaHelpComponentModelImpl.class);
    }


    /**
     * Tests the model when valid data is available.
     *
     * <p>
     * Verifies:
     * </p>
     * <ul>
     *     <li>Model adapts successfully</li>
     *     <li>Heading is correctly mapped</li>
     *     <li>Exported type is correct</li>
     *     <li>Helping icons list contains expected values</li>
     * </ul>
     */
    @Test
    void testModelWithData() {

        assertNotNull(model);
        assertEquals("We are here to help you", model.getHeading());
        assertEquals(LycaHelpComponentModelImpl.RESOURCE_TYPE, model.getExportedType());
        List<HelperComponentsModel> icons = model.getHelpingIcons();
        assertNotNull(icons);
        assertEquals(2, icons.size());
        HelperComponentsModel item1 = icons.get(0);
        assertEquals("/content/dam/icon1.png", item1.getIcon());
        assertEquals("https://example.com/1", item1.getIconLink());
        assertEquals("Prepaid plans FAQ", item1.getIconHeading());
        HelperComponentsModel item2 = icons.get(1);
        assertEquals("/content/dam/icon2.png", item2.getIcon());
        assertEquals("https://example.com/2", item2.getIconLink());
        assertEquals("Switch to Lyca", item2.getIconHeading());
    }


    /**
     * Tests the model when resource contains no authored data.
     *
     * <p>
     * Verifies:
     * </p>
     * <ul>
     *     <li>Model adapts successfully from empty resource</li>
     *     <li>Heading returns null</li>
     *     <li>Helping icons return null</li>
     *     <li>Exported type remains valid</li>
     * </ul>
     */
    @Test
    void testModelWithEmptyResource() {
        context.create().resource("/content/empty",
                "sling:resourceType", LycaHelpComponentModelImpl.RESOURCE_TYPE);
        Resource resource = context.resourceResolver().getResource("/content/empty");
        LycaHelpComponentModelImpl emptyModel = resource.adaptTo(LycaHelpComponentModelImpl.class);
        assertNotNull(emptyModel);
        assertNull(emptyModel.getHeading());
        assertNull(emptyModel.getHelpingIcons());
        assertEquals(LycaHelpComponentModelImpl.RESOURCE_TYPE, emptyModel.getExportedType());
    }
}