package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargeModel;
import com.adobe.aem.lyca.core.models.LycaReacargePlanTab;
import com.adobe.aem.lyca.core.models.LycaReacargePlans;
import com.adobe.aem.lyca.core.models.LycaReacargeTopUpAmount;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link LycaReacargeModelImpl}.
 * <p>
 * Verifies full component model including properties,
 * child resources, and exported type.
 * </p>
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaReacargeModelImplTest {

    /** AEM mock context used for testing Sling Models */
    private final AemContext context = new AemContext();

    /** Model instance under test */
    private LycaReacargeModel model;

    /**
     * Sets up the test environment before each test case.
     *
     * <p>Loads JSON content into the mock repository and adapts
     * the resource into the Sling Model.</p>
     */
    @BeforeEach
    void setUp() {
        context.load().json("/lycarecharge.json", "/content/recharge");

        Resource resource = context.resourceResolver()
                .getResource("/content/recharge");

        assertNotNull(resource);

        model = resource.adaptTo(LycaReacargeModel.class);

        assertNotNull(model);
    }

    /**
     * Tests basic component-level fields such as title,
     * description, operator details, and input placeholders.
     */
    @Test
    void testBasicFields() {
        assertEquals("Recharge", model.getTitle());
        assertTrue(model.getDescription().contains("top-up"));
        assertEquals("LycaMobile", model.getOperatorTitle());
        assertEquals("+91", model.getCountryCode());
        assertEquals("Enter a LycaMobile Number", model.getPhoneInputPlaceholder());
        assertEquals("Save more with SIM only plan", model.getPlansSectionTitle());
    }

    /**
     * Tests top-up section and payment-related fields.
     */
    @Test
    void testTopUpAndPaymentFields() {
        assertEquals("Top up your credit now", model.getTopUpTitle());
        assertEquals("Payment details", model.getPaymentSectionTitle());
        assertTrue(model.getPaymentDescription().contains("debit card"));
        assertEquals("Order summary", model.getOrderSummaryTitle());
        assertEquals("Regulatory recovery fees", model.getRegulatoryFeeLabel());
        assertEquals(6.0, model.getRegulatoryFeePercentage());
        assertEquals("Total", model.getTotalAmountLabel());
        assertTrue(model.getTermsText().contains("Terms"));
        assertEquals("Confirm & Pay", model.getCtaButtonText());
    }

    /**
     * Tests the plan tabs child resource list.
     *
     * <p>Validates size and individual tab properties.</p>
     */
    @Test
    void testPlanTabs() {
        List<LycaReacargePlanTab> tabs = model.getPlanTabs();

        assertNotNull(tabs);
        assertEquals(3, tabs.size());

        assertEquals("30 days plans", tabs.get(0).getTabLabel());
        assertEquals("30days", tabs.get(0).getTabKey());
    }

    /**
     * Tests the recharge plans child resource list.
     *
     * <p>Validates plan attributes such as data, price, and validity.</p>
     */
    @Test
    void testPlans() {
        List<LycaReacargePlans> plans = model.getPlans();

        assertNotNull(plans);
        assertEquals(5, plans.size());

        assertEquals("30days", plans.get(0).getTabKey());
        assertEquals("500MB", plans.get(0).getData());
        assertEquals("$15.00", plans.get(0).getPrice());
        assertEquals("30 days", plans.get(0).getValidity());
    }

    /**
     * Tests the top-up amounts child resource list.
     *
     * <p>Validates amount values and labels.</p>
     */
    @Test
    void testTopUpAmounts() {
        List<LycaReacargeTopUpAmount> topUps = model.getTopUpAmounts();

        assertNotNull(topUps);
        assertEquals(3, topUps.size());

        assertEquals("$10.00", topUps.get(0).getAmount());
        assertEquals("Min Save", topUps.get(0).getLabel());
    }

    /**
     * Verifies that the exported resource type
     * matches the expected component path.
     */
    @Test
    void testExportedType() {
        assertEquals(
                LycaReacargeModelImpl.RESOURCE_PATH,
                model.getExportedType()
        );
    }

    /**
     * Ensures that all child resource collections
     * are properly initialized and not null.
     */
    @Test
    void testNotNullCollections() {
        assertNotNull(model.getPlanTabs());
        assertNotNull(model.getPlans());
        assertNotNull(model.getTopUpAmounts());
    }
}
