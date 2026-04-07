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

@ExtendWith(AemContextExtension.class)
class BestSimPlansModelImplTest {

    private final AemContext context = new AemContext();

    private BestSimPlansModel model;

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

    @Test
    void testModelAdaptation() {
        assertNotNull(model);
    }

    @Test
    void testGetHeading() {
        assertEquals("Best SIM Plans", model.getHeading());
    }

    @Test
    void testGetFragmentParentPath() {
        assertEquals("/content/dam/lyca/sim-plans", model.getFragmentParentPath());
    }

    @Test
    void testIsHideText() {
        assertTrue(model.isHideText());
    }

    @Test
    void testGetCtaLabel() {
        assertEquals("View Plans", model.getCtaLabel());
    }

    @Test
    void testGetCtaLink() {
        assertEquals("/content/lyca/us/en/plans", model.getCtaLink());
    }

    @Test
    void testGetExportedType() {
        assertEquals("lyca-spa-react/components/content/best-sim-plans", model.getExportedType());
    }

    @Test
    void testPlansListNotNull() {
        assertNotNull(model.getPlans(), "Plans list should not be null");
    }

    @Test
    void testPlansListInitialized() {
        assertNotNull(model.getPlans());
        assertTrue(model.getPlans() instanceof ArrayList, "Plans should be initialized as ArrayList");
    }

    @Test
    void testPlansListNotEmpty() {
        assertNotNull(model.getPlans());
        assertFalse(model.getPlans().isEmpty(), "Plans list should not be empty");
    }

    @Test
    void testPlansSize() {
        assertEquals(2, model.getPlans().size());
    }

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