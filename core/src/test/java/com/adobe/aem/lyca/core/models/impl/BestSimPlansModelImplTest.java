package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.BestSimPlansModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class BestSimPlansModelImplTest {

    private final AemContext context = new AemContext();

    private BestSimPlansModel model;

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(BestSimPlansModelImpl.class);

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
        assertEquals(
                "lyca-spa-react/components/content/best-sim-plans",
                model.getExportedType()
        );
    }
}