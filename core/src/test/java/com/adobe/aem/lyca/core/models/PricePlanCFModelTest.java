package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class PricePlanCFModelTest {

    private final AemContext context = new AemContext();
    private PricePlanCFModel model;

    @BeforeEach
    void init() {
        context.addModelsForClasses(PricePlanCFModel.class);
        context.load().json("/priceplancf.json", "/content");
    }

    @Test
    void testValidPricePlan() {
        Resource resource = context.resourceResolver().getResource("/content/priceplan-valid");
        assertNotNull(resource);

        model = resource.adaptTo(PricePlanCFModel.class);

        assertNotNull(model);
        assertEquals("Starter", model.getPlanTitle());
        assertEquals(5.0, model.getPriceMonthly());
        assertEquals(50.0, model.getPriceYearly());
        assertEquals("5GB Monthly Plan", model.getDataLimit());
        assertNotNull(model.getFeatures());
        assertEquals(3, model.getFeatures().length);
        assertFalse(model.isPopular());
        assertEquals("Choose Plan", model.getCtaLabel());
        assertEquals("/plans/starter", model.getCtaLink());
    }

}