package com.adobe.aem.lyca.core.models;

import com.adobe.aem.lyca.core.models.impl.PricePlanModelImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link PricePlanModelImpl}.
 *
 * This class verifies:
 * - Sling Model adaptation
 * - Correct mapping of properties from resource
 * - Handling of empty, partial, and invalid values
 * - Exported resource type
 *
 * Uses AEM Mock (AemContext) to simulate repository content.
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class PricePlanModelImplTest {

    /** AEM mock context for simulating repository */
    private final AemContext context = new AemContext();

    /** Model under test */
    private PricePlanModel pricePlanModel;

    /**
     * Setup method executed before each test.
     *
     * Loads JSON test data and adapts resource
     * to PricePlanModel.
     */
    @BeforeEach
    void init() {
        /** Register Sling Model */
        context.addModelsForClasses(PricePlanModelImpl.class);

        /** Load test JSON into mock repository */
        context.load().json("/priceplan.json", "/content");

        /** Get test resource */
        Resource resource = context.resourceResolver().getResource("/content/priceplan-valid");

        /** Ensure resource exists */
        assertNotNull(resource);

        /** Adapt resource to model */
        pricePlanModel = resource.adaptTo(PricePlanModel.class);
    }

    /**
     * Test to verify model is successfully adapted.
     */
    @Test
    void testModelNotNull() {
        assertNotNull(pricePlanModel);
    }

    /**
     * Test to verify heading value mapping.
     */
    @Test
    void testHeading() {
        assertEquals("Choose Your Plan", pricePlanModel.getPricePlanHeading());
    }

    /**
     * Test to verify monthly text mapping.
     */
    @Test
    void testMonthlyText() {
        assertEquals("Monthly", pricePlanModel.getPricePlanMonthlyText());
    }

    /**
     * Test to verify yearly text mapping.
     */
    @Test
    void testYearlyText() {
        assertEquals("Yearly", pricePlanModel.getPricePlanYearlyText());
    }

    /**
     * Test to verify plan cards path mapping.
     */
    @Test
    void testPlanCardsPath() {
        assertEquals("/content/lyca/plans", pricePlanModel.getPlanCardsPath());
    }

    /**
     * Test to verify "Find Plan" label and link.
     */
    @Test
    void testFindPlan() {
        assertEquals("Find a Plan", pricePlanModel.getFindPlanLabel());
        assertEquals("/content/lyca/find-plan", pricePlanModel.getFindPlanLink());
    }

    /**
     * Test to verify "View All Plans" label.
     */
    @Test
    void testAllPlan() {
        assertEquals("View All Plans", pricePlanModel.getAllPlanLabel());
    }

    /**
     * Test to verify exported resource type.
     */
    @Test
    void testExportedType() {
        assertEquals("lyca-spa-react/components/priceplan", pricePlanModel.getExportedType());
    }

    /**
     * Test to verify behavior when all values are empty.
     *
     * Ensures model returns empty strings where applicable.
     */
    @Test
    void testEmptyValues() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-empty");
        assertNotNull(resource);

        PricePlanModel model = resource.adaptTo(PricePlanModel.class);
        assertNotNull(model);

        assertEquals("", model.getPricePlanHeading());
        assertEquals("", model.getPricePlanMonthlyText());
        assertEquals("", model.getPricePlanYearlyText());
        assertEquals("", model.getPlanCardsPath());
        assertEquals("", model.getFindPlanLabel());
        assertEquals("", model.getFindPlanLink());
        assertEquals("", model.getAllPlanLabel());
        assertEquals("lyca-spa-react/components/priceplan", model.getExportedType());
    }

    /**
     * Test to verify behavior when only partial values are available.
     *
     * Ensures:
     * - Available fields are populated
     * - Missing fields return null
     */
    @Test
    void testPartialValues() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-partial");
        assertNotNull(resource);

        PricePlanModel model = resource.adaptTo(PricePlanModel.class);
        assertNotNull(model);

        assertEquals("Plans", model.getPricePlanHeading());
        assertEquals("Monthly", model.getPricePlanMonthlyText());
        assertNull(model.getPricePlanYearlyText());
        assertNull(model.getPlanCardsPath());
        assertNull(model.getFindPlanLabel());
        assertNull(model.getFindPlanLink());
        assertNull(model.getAllPlanLabel());
        assertEquals("lyca-spa-react/components/priceplan", model.getExportedType());
    }

    /**
     * Test to verify handling of invalid data types or unexpected values.
     *
     * Ensures:
     * - Values are mapped as-is
     * - Missing values return null
     */
    @Test
    void testInvalidValues() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-invalid");
        assertNotNull(resource);

        PricePlanModel model = resource.adaptTo(PricePlanModel.class);
        assertNotNull(model);

        assertEquals("123", model.getPricePlanHeading());
        assertEquals("true", model.getPricePlanMonthlyText());
        assertEquals("456", model.getPlanCardsPath());
        assertEquals("false", model.getAllPlanLabel());
        assertNull(model.getPricePlanYearlyText());
        assertNull(model.getFindPlanLabel());
        assertNull(model.getFindPlanLink());
        assertEquals("lyca-spa-react/components/priceplan", model.getExportedType());
    }
}