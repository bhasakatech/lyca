package com.adobe.aem.lyca.core.models;

import com.adobe.aem.lyca.core.models.Impl.CtaItemImpl;
import com.adobe.aem.lyca.core.models.Impl.LycaJoinModelImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class LycaJoinModelImplTest {

    private final AemContext context = new AemContext();

    private LycaJoinModel model;

    @BeforeEach
    void setUp() {

        context.addModelsForClasses(LycaJoinModelImpl.class, CtaItemImpl.class);

        // Load JSON
        context.load().json("/lycaJoinModel.json", "/content");

        // Set current resource
        context.currentResource("/content/test");

        model = context.request().adaptTo(LycaJoinModel.class);

        assertNotNull(model);
    }

    @Test
    void testGetTitle() {
        assertEquals("Sample Title", model.getTitle());
    }

    @Test
    void testGetDescription() {
        assertEquals("Sample Description", model.getDescription());
    }

    @Test
    void testGetCtaItems() {
        List<CtaItem> ctaItems = model.getCtaItems();

        assertNotNull(ctaItems);
        assertEquals(2, ctaItems.size());

        assertEquals("CTA 1", ctaItems.get(0).getLabel());
        assertEquals("/content/page1", ctaItems.get(0).getLink());

        assertEquals("CTA 2", ctaItems.get(1).getLabel());
    }

    @Test
    void testGetExportedType() {
        assertEquals(LycaJoinModelImpl.RESOURCE_TYPE, model.getExportedType());
    }

}