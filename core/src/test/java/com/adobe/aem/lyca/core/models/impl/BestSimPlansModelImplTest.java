package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.BestSimPlanItem;
import com.adobe.aem.lyca.core.models.BestSimPlansModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link BestSimPlansModelImpl}.
 *
 * <p>This test class verifies the behavior of the Best SIM Plans Sling Model,
 * including authored property mapping, exported resource type, and dynamic
 * plan item loading from Content Fragment-like mock resources.</p>
 *
 * <p>The test cases cover:</p>
 * <ul>
 *   <li>Successful model adaptation from the current request</li>
 *   <li>Validation of authored component properties such as heading, CTA label, and CTA link</li>
 *   <li>Verification of exported resource type</li>
 *   <li>Initialization and population of the plans list</li>
 *   <li>Correct mapping of plan details from mock content data</li>
 *   <li>Handling of edge cases such as missing fragment path, missing parent resource,
 *       missing master data, empty fragment path, and folders without child resources</li>
 * </ul>
 *
 * <p>This class uses AEM Mocks to simulate repository content and Sling Model adaptation.</p>
 */
@ExtendWith(AemContextExtension.class)
class BestSimPlansModelImplTest {

    /**
     * AEM mock context used to simulate repository content, resources,
     * and Sling Model adaptation during unit testing.
     */
    private final AemContext context = new AemContext();

    /**
     * Model instance under test.
     */
    private BestSimPlansModel model;

    /**
     * Sets up the AEM mock context before each test.
     *
     * <p>This method registers the Sling Model classes, loads mock JSON content,
     * sets the current resource, and adapts the request to the
     * {@link BestSimPlansModel}.</p>
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(BestSimPlansModelImpl.class, BestSimPlanItem.class);

        context.load().json(
                "/best-sim-plan-model.json",
                "/content"
        );

        context.currentResource("/content/best-sim-plans");
        model = context.request().adaptTo(BestSimPlansModel.class);
    }

    /**
     * Verifies that the request successfully adapts to the model.
     */
    @Test
    void testModelAdaptation() {
        assertNotNull(model);
    }

    /**
     * Verifies that the authored heading is correctly mapped.
     */
    @Test
    void testGetHeading() {
        assertEquals("Best SIM Plans", model.getHeading());
    }

    /**
     * Verifies that the configured fragment parent path is correctly returned.
     */
    @Test
    void testGetFragmentParentPath() {
        assertEquals("/content/dam/lyca/sim-plans", model.getFragmentParentPath());
    }

    /**
     * Verifies that the hideText flag is correctly mapped.
     */
    @Test
    void testIsHideText() {
        assertTrue(model.isHideText());
    }

    /**
     * Verifies that the CTA label is correctly returned.
     */
    @Test
    void testGetCtaLabel() {
        assertEquals("View Plans", model.getCtaLabel());
    }

    /**
     * Verifies that the CTA link is correctly returned.
     */
    @Test
    void testGetCtaLink() {
        assertEquals("/content/lyca/us/en/plans", model.getCtaLink());
    }

    /**
     * Verifies that the exported resource type matches the component resource type.
     */
    @Test
    void testGetExportedType() {
        assertEquals("lyca-spa-react/components/content/best-sim-plans", model.getExportedType());
    }

    /**
     * Verifies that the plans list is never null after model initialization.
     */
    @Test
    void testPlansListNotNull() {
        assertNotNull(model.getPlans(), "Plans list should not be null");
    }

    /**
     * Verifies that the plans list is initialized as an {@link ArrayList}.
     */
    @Test
    void testPlansListInitialized() {
        assertNotNull(model.getPlans());
        assertTrue(model.getPlans() instanceof ArrayList, "Plans should be initialized as ArrayList");
    }

    /**
     * Verifies that the plans list is populated when valid fragment data exists.
     */
    @Test
    void testPlansListNotEmpty() {
        assertNotNull(model.getPlans());
        assertFalse(model.getPlans().isEmpty(), "Plans list should not be empty");
    }

    /**
     * Verifies the expected number of plan items loaded from mock content.
     */
    @Test
    void testPlansSize() {
        assertEquals(2, model.getPlans().size());
    }

    /**
     * Verifies that the first plan item is correctly mapped from mock fragment data.
     */
    @Test
    void testFirstPlanData() {
        List<BestSimPlanItem> plans = model.getPlans();
        assertEquals(2, plans.size());

        BestSimPlanItem first = plans.get(0);

        assertEquals("plan1", first.getId());
        assertEquals("LYCA", first.getName());
        assertEquals("5", first.getMonthlyCost());
        assertEquals("5GB", first.getData());
        assertEquals("1000", first.getMinutes());
        assertEquals("1000", first.getTexts());
        assertEquals("No Contract", first.getContract());
        assertEquals("NO", first.getAnnualPriceRise());
        assertEquals("YES", first.getFreeEuRoaming());
    }

    /**
     * Verifies that the second plan item is correctly mapped from mock fragment data.
     */
    @Test
    void testSecondPlanData() {
        List<BestSimPlanItem> plans = model.getPlans();
        assertEquals(2, plans.size());

        BestSimPlanItem second = plans.get(1);

        assertEquals("plan2", second.getId());
        assertEquals("EE", second.getName());
        assertEquals("27", second.getMonthlyCost());
        assertEquals("5GB", second.getData());
        assertEquals("Unlimited", second.getMinutes());
        assertEquals("Unlimited", second.getTexts());
        assertEquals("Not Contract", second.getContract());
        assertEquals("NO", second.getAnnualPriceRise());
        assertEquals("NO", second.getFreeEuRoaming());
    }

    /**
     * Verifies that the model initializes safely and returns an empty plans list
     * when the fragment parent path is not authored.
     */
    @Test
    void testInitWhenFragmentParentPathMissing() {
        context.create().resource("/content/no-path-component",
                "sling:resourceType", "lyca-spa-react/components/content/best-sim-plans",
                "heading", "No Path Component",
                "hideText", true,
                "ctaLabel", "View Plans",
                "ctaLink", "/content/test");

        context.currentResource("/content/no-path-component");
        BestSimPlansModel localModel = context.request().adaptTo(BestSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPlans());
        assertTrue(localModel.getPlans().isEmpty());
    }

    /**
     * Verifies that the model returns an empty plans list when the configured
     * parent resource path does not exist.
     */
    @Test
    void testInitWhenParentResourceMissing() {
        context.create().resource("/content/missing-parent-component",
                "sling:resourceType", "lyca-spa-react/components/content/best-sim-plans",
                "heading", "Missing Parent",
                "fragmentParentPath", "/content/dam/lyca/not-found",
                "hideText", true,
                "ctaLabel", "View Plans",
                "ctaLink", "/content/test");

        context.currentResource("/content/missing-parent-component");
        BestSimPlansModel localModel = context.request().adaptTo(BestSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPlans());
        assertTrue(localModel.getPlans().isEmpty());
    }

    /**
     * Verifies that child resources without master data are skipped during initialization.
     */
    @Test
    void testInitSkipsChildWithoutMasterData() {
        context.create().resource("/content/dam/lyca/empty-plans");
        context.create().resource("/content/dam/lyca/empty-plans/plan-x");

        context.create().resource("/content/empty-plans-component",
                "sling:resourceType", "lyca-spa-react/components/content/best-sim-plans",
                "heading", "Empty Plans",
                "fragmentParentPath", "/content/dam/lyca/empty-plans",
                "hideText", true,
                "ctaLabel", "View Plans",
                "ctaLink", "/content/test");

        context.currentResource("/content/empty-plans-component");
        BestSimPlansModel localModel = context.request().adaptTo(BestSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPlans());
        assertTrue(localModel.getPlans().isEmpty());
    }

    /**
     * Verifies that the model initializes safely when the fragment parent path is empty.
     */
    @Test
    void testInitWhenFragmentParentPathEmpty() {
        context.create().resource("/content/empty-path-component",
                "sling:resourceType", "lyca-spa-react/components/content/best-sim-plans",
                "heading", "Empty Path Component",
                "fragmentParentPath", "",
                "hideText", true,
                "ctaLabel", "View Plans",
                "ctaLink", "/content/test");

        context.currentResource("/content/empty-path-component");
        BestSimPlansModel localModel = context.request().adaptTo(BestSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPlans());
        assertTrue(localModel.getPlans().isEmpty());
    }

    /**
     * Verifies that the model returns an empty plans list when the parent resource exists
     * but does not contain any child plan resources.
     */
    @Test
    void testInitWhenParentResourceHasNoChildren() {
        context.create().resource("/content/dam/lyca/no-plans");

        context.create().resource("/content/no-children-component",
                "sling:resourceType", "lyca-spa-react/components/content/best-sim-plans",
                "heading", "No Children",
                "fragmentParentPath", "/content/dam/lyca/no-plans",
                "hideText", true,
                "ctaLabel", "View Plans",
                "ctaLink", "/content/test");

        context.currentResource("/content/no-children-component");
        BestSimPlansModel localModel = context.request().adaptTo(BestSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPlans());
        assertTrue(localModel.getPlans().isEmpty());
    }
}