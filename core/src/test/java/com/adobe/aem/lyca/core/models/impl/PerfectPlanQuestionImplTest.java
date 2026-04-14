package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PerfectPlanQuestion;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link PerfectPlanQuestionImpl}.
 *
 * <p>
 * This test verifies the behavior of the model, including:
 * <ul>
 *     <li>Correct retrieval of question text</li>
 *     <li>Handling of options list</li>
 *     <li>Limiting options to a maximum of 4</li>
 *     <li>Handling null or empty options</li>
 * </ul>
 * </p>
 *
 * <p>
 * Uses {@link AemContext} to mock AEM resources and load test JSON data.
 * </p>
 */
@ExtendWith(AemContextExtension.class)
class PerfectPlanQuestionImplTest {

    /**
     * AEM context for resource and model adaptation.
     */
    private final AemContext context = new AemContext();

    /**
     * Model instance under test.
     */
    private PerfectPlanQuestion model;

    /**
     * Sets up the test environment before each test execution.
     *
     * <p>
     * Loads JSON test data and registers the Sling Model.
     * </p>
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(PerfectPlanQuestionImpl.class);
        context.load().json("/perfactPlan.json", "/content");
    }

    /**
     * Tests scenario where options size is less than or equal to 4.
     *
     * <p>
     * Verifies:
     * <ul>
     *     <li>Options list is not null</li>
     *     <li>Question text is correctly mapped</li>
     *     <li>Options size is exactly 4</li>
     * </ul>
     * </p>
     */
    @Test
    void testOptions_SizeLessThanOrEqualFour() {
        model = context.currentResource("/content/perfactPlan/perfectPlanQuestions/item0")
                .adaptTo(PerfectPlanQuestion.class);

        List<String> options = model.getOptions();
        assertNotNull(options);
        assertEquals("How much data do you use?", model.getQuestion());
        assertEquals(4, options.size());
    }

    /**
     * Tests scenario where options are null.
     *
     * <p>
     * Verifies:
     * <ul>
     *     <li>Returned list is not null</li>
     *     <li>Returned list is empty</li>
     * </ul>
     * </p>
     */
    @Test
    void testOptions_NullCase() {
        model = context.currentResource("/content/perfactPlan/perfectPlanQuestions/item1")
                .adaptTo(PerfectPlanQuestion.class);

        assertNotNull(model.getOptions());
        assertTrue(model.getOptions().isEmpty());
    }

    /**
     * Tests scenario where options size is greater than 4.
     *
     * <p>
     * Verifies:
     * <ul>
     *     <li>Options list is not null</li>
     *     <li>Options are limited to 4 items</li>
     * </ul>
     * </p>
     */
    @Test
    void testOptions_SizeGreaterThanFour() {
        model = context.currentResource("/content/perfactPlan/perfectPlanQuestions/item2")
                .adaptTo(PerfectPlanQuestion.class);

        List<String> options = model.getOptions();
        assertNotNull(options);
        assertEquals(4, options.size());
    }
}