package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaExclusiveOffers;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(AemContextExtension.class)
class LycaExclusiveOffersImplTest {
    private final AemContext context = new AemContext();
    private LycaExclusiveOffers model;
    @BeforeEach
    void init() {
        context.addModelsForClasses(LycaExclusiveOffersImpl.class);
        context.load().json("/lycaexclusiveoffers.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/lycaexclusiveoffers");
        assertNotNull(resource);
        model = resource.adaptTo(LycaExclusiveOffers.class);
    }
    @Test
    void testModelNotNull() {
        assertNotNull(model);
    }
    @Test
    void testHeading() {
        assertEquals("Sign up to get exclusive offers", model.getHeading());
    }
    @Test
    void testInputPlaceholderText() {
        assertEquals("Enter your email address", model.getInputPlaceholderText());
    }
    @Test
    void testCtaLabel() {
        assertEquals("Join our offers club", model.getCtaLabel());
    }
    @Test
    void testCtaLink() {
        assertEquals("#", model.getCtaLink());
    }
    @Test
    void testDescription() {
        assertTrue(model.getDescription().contains("relevant offers"));
    }
    @Test
    void testExportedType() {
        assertEquals(
                "lyca-spa-react/components/lycaexclusiveoffers",
                model.getExportedType()
        );
    }
}
