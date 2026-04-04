package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class PerfectPlanQuestionImplTest {
    private final AemContext context = new AemContext();
    private PerfectPlanQuestion model;

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(PerfectPlanQuestionImpl.class);
        context.load().json("/perfactPlan.json", "/content");
    }

    @Test
    void testOptions_SizeLessThanOrEqualFour() {
        model = context.currentResource("/content/perfactPlan/perfectPlanQuestions/item0")
                .adaptTo(PerfectPlanQuestion.class);

        List<String> options = model.getOptions();
        assertNotNull(options);
        assertEquals("How much data do you use?", model.getQuestion());
        assertEquals(4, options.size());
    }

    @Test
    void testOptions_NullCase() {
        model = context.currentResource("/content/perfactPlan/perfectPlanQuestions/item1")
                .adaptTo(PerfectPlanQuestion.class);

        assertNotNull(model.getOptions());
        assertTrue(model.getOptions().isEmpty());
    }

    @Test
    void testOptions_SizeGreaterThanFour() {
        model = context.currentResource("/content/perfactPlan/perfectPlanQuestions/item2")
                .adaptTo(PerfectPlanQuestion.class);

        List<String> options = model.getOptions();
        assertNotNull(options);
        assertEquals(4, options.size());
    }
}