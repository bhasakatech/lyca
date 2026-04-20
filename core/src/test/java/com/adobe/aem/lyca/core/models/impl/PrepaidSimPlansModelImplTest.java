package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PrepaidSimPlanItem;
import com.adobe.aem.lyca.core.models.PrepaidSimPlansModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link PrepaidSimPlansModelImpl}.
 *
 * <p>This test class verifies authored property mapping, exported resource type,
 * and Content Fragment based plan item loading for the Prepaid SIM Plans component.</p>
 */
@ExtendWith(AemContextExtension.class)
class PrepaidSimPlansModelImplTest {

    /**
     * AEM mock context used to simulate repository and resource structure.
     */
    private final AemContext context = new AemContext();


    /**
     * Model instance under test.
     */
    private PrepaidSimPlansModel model;

    /**
     * Sets up the AEM mock context before each test.
     *
     * <p>This method registers Sling Model classes, loads mock JSON content,
     * sets the current resource, and adapts the request to
     * {@link PrepaidSimPlansModel}.</p>
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(PrepaidSimPlansModelImpl.class, PrepaidSimPlanItem.class);

        context.load().json(
                "/prepaid-sim-plan-model.json",
                "/content"
        );

        context.currentResource("/content/prepaid-sim-plans");
        model = context.request().adaptTo(PrepaidSimPlansModel.class);
    }

    /**
     * Verifies that the request successfully adapts to the model.
     */
    @Test
    void testModelAdaptation() {
        assertNotNull(model);
    }


    /**
     * Verifies heading mapping.
     */
    @Test
    void testGetHeading() {
        assertEquals("Prepaid SIM Plans", model.getHeading());
    }


    /**
     * Verifies description mapping.
     */
    @Test
    void testGetDescription() {
        assertEquals("Choose your prepaid plan", model.getDescription());
    }

    /**
     * Verifies toggle switch values.
     */
    @Test
    void testGetToggleSwitchButton() {
        assertNotNull(model.getToggleSwitchButton());
        assertEquals(2, model.getToggleSwitchButton().length);
        assertEquals("Popular", model.getToggleSwitchButton()[0]);
        assertEquals("Unlimited", model.getToggleSwitchButton()[1]);
    }

    /**
     * Verifies fragment parent path.
     */
    @Test
    void testGetFragmentParentPath() {
        assertEquals("/content/dam/lyca/prepaid-plans", model.getFragmentParentPath());
    }

    /**
     * Verifies existing customer title.
     */
    @Test
    void testGetExistingCustomTitle() {
        assertEquals("Already a customer?", model.getExistingCustomTitle());
    }


    /**
     * Verifies existing plan options.
     */
    @Test
    void testGetExistingPlanOptions() {
        assertNotNull(model.getExistingPlanOptions());
        assertEquals(2, model.getExistingPlanOptions().length);
        assertEquals("Recharge", model.getExistingPlanOptions()[0]);
        assertEquals("Upgrade", model.getExistingPlanOptions()[1]);
    }

    /**
     * Verifies CTA link mapping.
     */
    @Test
    void testGetCtaLink() {
        assertEquals("/content/lyca/us/en/prepaid", model.getCtaLink());
    }

    /**
     * Verifies exported resource type.
     */
    @Test
    void testGetExportedType() {
        assertEquals("lyca-spa-react/components/content/prepaid-sim-plans", model.getExportedType());
    }

    /**
     * Verifies plan list contains data.
     */

    @Test
    void testPlansListNotNull() {
        assertNotNull(model.getPrepaidSimPlanItems(), "Plan list should not be null");
    }

    /**
     * Verifies plan list is initialized.
     */
    @Test
    void testPlansListInitialized() {
        assertNotNull(model.getPrepaidSimPlanItems());
        assertTrue(model.getPrepaidSimPlanItems() instanceof ArrayList,
                "Plan list should be initialized as ArrayList");
    }


    /**
     * Verifies plan list contains data.
     */
    @Test
    void testPlansListNotEmpty() {
        assertNotNull(model.getPrepaidSimPlanItems());
        assertFalse(model.getPrepaidSimPlanItems().isEmpty(), "Plan list should not be empty");
    }

    /**
     * Verifies number of plans loaded.
     */
    @Test
    void testPlansSize() {
        assertEquals(2, model.getPrepaidSimPlanItems().size());
    }

    /**
     * Verifies first plan data mapping.
     */
    @Test
    void testFirstPlanData() {
        List<PrepaidSimPlanItem> plans = model.getPrepaidSimPlanItems();
        assertEquals(2, plans.size());

        PrepaidSimPlanItem first = plans.get(0);

        assertEquals("plan1", first.getName());
        assertEquals("Data", first.getPlanTitle());
        assertEquals("Unlimited", first.getSubTitle());
        assertEquals("Popular", first.getCategory());
        assertEquals("299", first.getPrice());
        assertEquals("28 days", first.getValidity());
        assertEquals(2, first.getFeatures().size());
        assertEquals("Feature 1", first.getFeatures().get(0));
        assertEquals("Feature 2", first.getFeatures().get(1));
        assertEquals("Buy Now", first.getBuyNowCtaLabel());
        assertEquals("/buy", first.getBuyNowCtaLink());
        assertEquals("Add to Basket", first.getAddToBasketCtaLabel());
        assertEquals("/cart", first.getAddToBasketCtaLink());
        assertEquals("View More", first.getViewMoreLabel());
        assertEquals("/details", first.getViewMoreLink());
        assertEquals("Get Immediate Activation with eSIM", first.getFooterText());
        assertEquals("/content/dam/lyca/prepaid-plans/plan1", first.getPath());
        assertEquals("/content/dam/img1.png", first.getImage());
    }

    /**
     * Verifies second plan data mapping.
     */
    @Test
    void testSecondPlanData() {
        List<PrepaidSimPlanItem> plans = model.getPrepaidSimPlanItems();
        assertEquals(2, plans.size());

        PrepaidSimPlanItem second = plans.get(1);

        assertEquals("plan2", second.getName());
        assertEquals("Calls", second.getPlanTitle());
        assertEquals("Unlimited Calls", second.getSubTitle());
        assertEquals("Unlimited", second.getCategory());
        assertEquals("499", second.getPrice());
        assertEquals("56 days", second.getValidity());
        assertNotNull(second.getFeatures());
        assertTrue(second.getFeatures().isEmpty(), "Features should be empty when not authored");
        assertEquals("Buy Now", second.getBuyNowCtaLabel());
        assertEquals("/buy2", second.getBuyNowCtaLink());
        assertEquals("Add to Basket", second.getAddToBasketCtaLabel());
        assertEquals("/cart2", second.getAddToBasketCtaLink());
        assertEquals("View More", second.getViewMoreLabel());
        assertEquals("/details2", second.getViewMoreLink());
        assertEquals("Special Offer", second.getFooterText());
        assertEquals("/content/dam/lyca/prepaid-plans/plan2", second.getPath());
        assertEquals("/content/dam/img2.png", second.getImage());
    }

    /**
     * Verifies safe initialization when fragment path is missing.
     */
    @Test
    void testInitWhenFragmentParentPathMissing() {
        context.create().resource("/content/no-path-component",
                "sling:resourceType", "lyca-spa-react/components/content/prepaid-sim-plans",
                "heading", "No Path Component",
                "description", "No fragment path");

        context.currentResource("/content/no-path-component");
        PrepaidSimPlansModel localModel = context.request().adaptTo(PrepaidSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPrepaidSimPlanItems());
        assertTrue(localModel.getPrepaidSimPlanItems().isEmpty());
    }

    /**
     * Verifies safe initialization when parent resource is missing.
     */
    @Test
    void testInitWhenFragmentParentPathEmpty() {
        context.create().resource("/content/empty-path-component",
                "sling:resourceType", "lyca-spa-react/components/content/prepaid-sim-plans",
                "heading", "Empty Path Component",
                "fragmentParentPath", "");

        context.currentResource("/content/empty-path-component");
        PrepaidSimPlansModel localModel = context.request().adaptTo(PrepaidSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPrepaidSimPlanItems());
        assertTrue(localModel.getPrepaidSimPlanItems().isEmpty());
    }

    /**
     * Verifies safe initialization when parent resource is missing.
     */
    @Test
    void testInitWhenParentResourceMissing() {
        context.create().resource("/content/missing-parent-component",
                "sling:resourceType", "lyca-spa-react/components/content/prepaid-sim-plans",
                "heading", "Missing Parent",
                "fragmentParentPath", "/content/dam/lyca/not-found");

        context.currentResource("/content/missing-parent-component");
        PrepaidSimPlansModel localModel = context.request().adaptTo(PrepaidSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPrepaidSimPlanItems());
        assertTrue(localModel.getPrepaidSimPlanItems().isEmpty());
    }

    /**
     * Verifies child without master data is ignored.
     */
    @Test
    void testInitSkipsChildWithoutMasterData() {
        context.create().resource("/content/dam/lyca/empty-plans");
        context.create().resource("/content/dam/lyca/empty-plans/plan-x");

        context.create().resource("/content/empty-plans-component",
                "sling:resourceType", "lyca-spa-react/components/content/prepaid-sim-plans",
                "heading", "Empty Plans",
                "fragmentParentPath", "/content/dam/lyca/empty-plans");

        context.currentResource("/content/empty-plans-component");
        PrepaidSimPlansModel localModel = context.request().adaptTo(PrepaidSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPrepaidSimPlanItems());
        assertTrue(localModel.getPrepaidSimPlanItems().isEmpty());
    }

    @Test
    void testInitWhenParentResourceHasNoChildren() {
        context.create().resource("/content/dam/lyca/no-plans");

        context.create().resource("/content/no-children-component",
                "sling:resourceType", "lyca-spa-react/components/content/prepaid-sim-plans",
                "heading", "No Children",
                "fragmentParentPath", "/content/dam/lyca/no-plans");

        context.currentResource("/content/no-children-component");
        PrepaidSimPlansModel localModel = context.request().adaptTo(PrepaidSimPlansModel.class);

        assertNotNull(localModel);
        assertNotNull(localModel.getPrepaidSimPlanItems());
        assertTrue(localModel.getPrepaidSimPlanItems().isEmpty());
    }

    @Test
    void testInitSkipsNullChildAndProcessesValidChildren() {
        context.create().resource("/content/dam/lyca/mixed-plans");
        context.create().resource("/content/dam/lyca/mixed-plans/plan-valid/jcr:content/data/master",
                "planTitle", "Mixed Plan",
                "subTitle", "Test SubTitle",
                "category", "Popular",
                "price", "199",
                "validity", "14 days",
                "features", new String[]{"A", "B"},
                "buyNowCtaLabel", "Buy",
                "buyNowCtaLink", "/buy-mixed",
                "addToBasketCtaLabel", "Basket",
                "addToBasketCtaLink", "/basket-mixed",
                "viewMoreLabel", "More",
                "viewMoreLink", "/more-mixed",
                "footerText", "Mixed Footer",
                "image", "/content/dam/mixed.png");

        context.create().resource("/content/mixed-plans-component",
                "sling:resourceType", "lyca-spa-react/components/content/prepaid-sim-plans",
                "heading", "Mixed Plans",
                "fragmentParentPath", "/content/dam/lyca/mixed-plans");

        context.currentResource("/content/mixed-plans-component");
        PrepaidSimPlansModel localModel = context.request().adaptTo(PrepaidSimPlansModel.class);

        assertNotNull(localModel);
        assertEquals(1, localModel.getPrepaidSimPlanItems().size());

        PrepaidSimPlanItem item = localModel.getPrepaidSimPlanItems().get(0);
        assertEquals("plan-valid", item.getName());
        assertEquals("Mixed Plan", item.getPlanTitle());
    }
}