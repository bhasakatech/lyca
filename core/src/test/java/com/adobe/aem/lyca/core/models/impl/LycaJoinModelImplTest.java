package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaJoinModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class LycaJoinModelImplTest {

    private final AemContext context = new AemContext();
    private LycaJoinModel model;

    @BeforeEach
    void setUp() {

        context.addModelsForClasses(LycaJoinModelImpl.class);
        context.load().json("/joinLyca.json", "/content");
        context.currentResource("/content/joinLyca");
        model = context.request().adaptTo(LycaJoinModel.class);
        assertNotNull(model, "Model should not be null");
    }

    @Test
    void testBasicFields() {
        assertEquals("Join Lyca", model.getTitle());
        assertEquals("Join description", model.getDescription());
        assertEquals("Main Heading", model.getHeading());
        assertEquals("Enter number", model.getPlaceholder());
        assertEquals("Submit", model.getSubmitText());
        assertEquals("/submit-link", model.getSubmitLink());
        assertEquals("Promo Text", model.getPromotionText());
    }

    @Test
    void testCtaItems() {
        assertNotNull(model.getCtaItems());
        assertEquals(2, model.getCtaItems().size());
    }

    @Test
    void testCtaButtons() {
        assertNotNull(model.getCtas());
        assertEquals(2, model.getCtas().size());
    }

    @Test
    void testExportedType() {
        assertEquals(LycaJoinModelImpl.RESOURCE_TYPE, model.getExportedType());
    }
}