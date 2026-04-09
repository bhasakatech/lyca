package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class LycaMobileAppComponentModelImplTest {

    private final AemContext context = new AemContext();

    private LycaMobileAppComponentModel model;

    @BeforeEach
    void setUp() {
        context.load().json(
                "/LycaMobileAppComponentModelImplTest.json",
                "/content"
        );

        Resource resource = context.resourceResolver().getResource("/content/test");

        model = resource.adaptTo(LycaMobileAppComponentModel.class);
    }
    @Test
    void testModelWithData() {

        assertNotNull(model);

        assertEquals("Test Heading", model.getMainHeading());

        assertNotNull(model.getMobileAppFeatures());
        assertEquals(2, model.getMobileAppFeatures().size());

        assertNotNull(model.getAppStoreComponents());
        assertEquals(2, model.getAppStoreComponents().size());

        assertEquals("Scan Me", model.getQRText());
        assertEquals("/content/dam/qr.png", model.getQRImage());
        assertEquals("/content/dam/mobile.png", model.getMobileImage());

        assertEquals(LycaMobileAppComponentModelImpl.RESOURCE_TYPE,
                model.getExportedType());
    }
    @Test
    void testModelWithEmptyData() {

        Resource resource = context.resourceResolver().getResource("/content/empty");

        LycaMobileAppComponentModel emptyModel =
                resource.adaptTo(LycaMobileAppComponentModel.class);

        assertNotNull(emptyModel);

        assertNull(emptyModel.getMainHeading());
        assertNull(emptyModel.getMobileAppFeatures());
        assertNull(emptyModel.getAppStoreComponents());
        assertNull(emptyModel.getQRText());
        assertNull(emptyModel.getQRImage());
        assertNull(emptyModel.getMobileImage());

        assertEquals(LycaMobileAppComponentModelImpl.RESOURCE_TYPE,
                emptyModel.getExportedType());
    }
}