package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PerfactPlanModel;
import com.adobe.aem.lyca.core.models.PerfectPlanQuestion;
import com.adobe.aem.lyca.core.services.PerfactPlanService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link PerfactPlanModelImpl}.
 *
 * <p>This test class uses AEM Mocks (AemContext) to simulate:
 * <ul>
 *     <li>Sling Model adaptation</li>
 *     <li>Content resource loading from JSON</li>
 *     <li>OSGi service mocking (PerfactPlanService)</li>
 * </ul>
 *
 * It ensures that:
 * <ul>
 *     <li>All component properties are correctly mapped</li>
 *     <li>Content fragment/service data is handled properly</li>
 *     <li>Edge cases like empty resources are handled safely</li>
 * </ul>
 */
@ExtendWith(AemContextExtension.class)
class PerfactPlanModelImplTest {

    /**
     * AEM testing context used to mock repository, services, and request.
     */
    private final AemContext context = new AemContext();

    /**
     * Sling Model under test
     */
    private PerfactPlanModel model;

    /**
     * Sets up the AEM test environment before each test:
     * <ul>
     *     <li>Registers Sling Models</li>
     *     <li>Mocks PerfactPlanService</li>
     *     <li>Loads test JSON content into repository</li>
     *     <li>Adapts current resource to model</li>
     * </ul>
     */
    @BeforeEach
    void setUp() {
        // Register Sling Model class
        context.addModelsForClasses(PerfactPlanModelImpl.class);

        // OSGi service behavior
        context.registerService(PerfactPlanService.class, new PerfactPlanService() {
            @Override
            public List<PerfactPlanItem> getPlans(String cfPath, ResourceResolver resolver) {
                return Collections.emptyList(); // simulate empty plans
            }
        });

        // Load test content structure
        context.load().json("/perfactPlan.json", "/content");
        // Set current resource for model adaptation
        context.currentResource("/content/perfactPlan");
        // Adapt Sling Model
        model = context.request().adaptTo(PerfactPlanModel.class);
    }

    /**
     * Validates basic component properties mapping from JCR resource.
     */
    @Test
    void testModelProperties() {
        assertNotNull(model);
        assertEquals("Find Your Perfect Plan", model.getHeading());
        assertEquals("/content/dam/icons/sample-icon.png", model.getIcon());
        assertEquals("The best plan for you is", model.getTitle());
        assertEquals("Based on your answers, we recommend:", model.getDescription());
        assertEquals("Get Started", model.getButtonText());
        assertEquals("/content/test/button-link", model.getButtonLink());
        assertEquals("View Other Plans", model.getOthersPlanText());
        assertEquals("/content/test/other-plans", model.getOthersPlanLink());
    }

    /**
     * Validates that questions are correctly mapped from content fragment / JSON.
     */
    @Test
    void testPerfectPlanQuestions() {
        List<PerfectPlanQuestion> questions = model.getPerfectPlanQuestions();
        assertNotNull(questions);
        assertEquals(3, questions.size());
        assertEquals("How much data do you use?", questions.get(0).getQuestion());
    }

    /**
     * Ensures service layer integration returns expected plan data.
     * Here service is mocked to return empty list.
     */
    @Test
    void testPlansFromService() {
        List<PerfactPlanItem> plans = model.getPlans();
        assertNotNull(plans);
        assertTrue(plans.isEmpty());
    }

    /**
     * Validates exported Sling Model type for SPA editor / JSON export.
     */
    @Test
    void testExportedType() {
        assertEquals(
                "lyca-spa-react/components/content/perfact-plan",
                model.getExportedType()
        );
    }

    /**
     * Edge case test:
     * Ensures model behaves correctly when resource is empty or minimal.
     */
    @Test
    void testEmptyResource() {
        context.currentResource("/content/perfactPlanEmpty");
        PerfactPlanModel emptyModel =
                context.request().adaptTo(PerfactPlanModel.class);
        assertEquals("Empty Plan", emptyModel.getHeading());
        assertNull(emptyModel.getPerfectPlanQuestions());
    }
}