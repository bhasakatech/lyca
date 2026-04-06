package com.adobe.aem.lyca.core.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class LycaHelpComponentModelImplTest {

    private final AemContext context = new AemContext();

    private LycaHelpComponentModelImpl model;

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(
                LycaHelpComponentModelImpl.class,
                ComponentsModel.class
        );
        context.load().json("/lycahelpcomponent.json", "/content/test");
        Resource resource = context.resourceResolver().getResource("/content/test");
        context.currentResource(resource);
        model = resource.adaptTo(LycaHelpComponentModelImpl.class);
    }

    @Test
    void testModelWithData() {

        assertNotNull(model);
        assertEquals("We are here to help you", model.getHeading());
        assertEquals(LycaHelpComponentModelImpl.RESOURCE_TYPE, model.getExportedType());
        List<ComponentsModel> icons = model.getHelpingIcons();
        assertNotNull(icons);
        assertEquals(2, icons.size());
        ComponentsModel item1 = icons.get(0);
        assertEquals("/content/dam/icon1.png", item1.getIcon());
        assertEquals("https://example.com/1", item1.getIconLink());
        assertEquals("Prepaid plans FAQ", item1.getIconHeading());
        ComponentsModel item2 = icons.get(1);
        assertEquals("/content/dam/icon2.png", item2.getIcon());
        assertEquals("https://example.com/2", item2.getIconLink());
        assertEquals("Switch to Lyca", item2.getIconHeading());
    }
    @Test
    void testModelWithEmptyResource() {
        context.create().resource("/content/empty",
                "sling:resourceType", LycaHelpComponentModelImpl.RESOURCE_TYPE);
        Resource resource = context.resourceResolver().getResource("/content/empty");
        LycaHelpComponentModelImpl emptyModel = resource.adaptTo(LycaHelpComponentModelImpl.class);
        assertNotNull(emptyModel);
        assertNull(emptyModel.getHeading());
        assertNull(emptyModel.getHelpingIcons());
        assertEquals(LycaHelpComponentModelImpl.RESOURCE_TYPE, emptyModel.getExportedType());
    }
}