package com.adobe.aem.lyca.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class TopInformationalBannerTextImplModelTest {

    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(TopInformationalBannerTextImplModel.class);

        // ✅ Create data manually instead of JSON
        context.create().resource("/content/topBanner",
                "sling:resourceType", TopInformationalBannerTextImplModel.RESOURCE_TYPE,
                "text", "<p>Hello from test</p>");

        context.create().resource("/content/topBannerEmpty",
                "sling:resourceType", TopInformationalBannerTextImplModel.RESOURCE_TYPE);
    }

    @Test
    void testGetText() {
        Resource resource = context.resourceResolver().getResource("/content/topBanner");

        TopInformationalBannerTextImplModel model =
                resource.adaptTo(TopInformationalBannerTextImplModel.class);

        assertEquals("<p>Hello from test</p>", model.getText());
    }

    @Test
    void testGetText_null() {
        Resource resource = context.resourceResolver().getResource("/content/topBannerEmpty");

        TopInformationalBannerTextImplModel model =
                resource.adaptTo(TopInformationalBannerTextImplModel.class);

        assertNull(model.getText());
    }

    @Test
    void testExportedType() {
        Resource resource = context.resourceResolver().getResource("/content/topBanner");

        TopInformationalBannerTextImplModel model =
                resource.adaptTo(TopInformationalBannerTextImplModel.class);

        assertEquals(
                TopInformationalBannerTextImplModel.RESOURCE_TYPE,
                model.getExportedType()
        );
    }
}