package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PerfactPlanModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class PerfactPlanModelImplTest {

    private final AemContext context = new AemContext();
    private PerfactPlanModel model;

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(PerfactPlanModelImpl.class);
        context.load().json("/perfactPlan.json", "/content");
    }

    @Test
    void testHeading() {
        context.currentResource("/content/perfactPlan");
        model = context.request().adaptTo(PerfactPlanModel.class);
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

    @Test
    void testPerfectPlanQuestions_Null() {
        context.currentResource("/content/perfactPlanEmpty");
        PerfactPlanModel model = context.request().adaptTo(PerfactPlanModelImpl.class);
        assertNull(model.getPerfectPlanQuestions());
    }

    @Test
    void testExportedType() {
        context.currentResource("/content/perfactPlan");
        PerfactPlanModel model = context.request().adaptTo(PerfactPlanModelImpl.class);
        assertEquals(PerfactPlanModelImpl.RESOURCE_TYPE, model.getExportedType()
        );
    }
}