package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.FooterModel;
import com.adobe.aem.lyca.core.models.InfoSectionModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit test class for {@link InfoSectionModelImpl}.
 * <p>
 * This class verifies the complete InfoSection component structure,
 * including basic.
 * </p>
 * <p>
 * It uses {@link AemContext} to mock AEM resources and adapts
 * the request to {@link InfoSectionModelImpl} for testing Sling Model behavior.
 * </p>
 * @author Jaya Chandra Reddy
 */
@ExtendWith(AemContextExtension.class)
class InfoSectionModelImplTest {

    /** AEM mock context */
    private final AemContext context = new AemContext();

    /** InfoSection Model Field */
    InfoSectionModel model;
    /**
     * Initializes the test setup by registering models,
     * loading mock JSON data, and adapting the request
     * to the {@link InfoSectionModel}.
     */
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(
               InfoSectionModelImpl.class
        );
        context.load().json("/info-section.json", "/content/info");
        Resource resource = context.resourceResolver().getResource("/content/info");
       model = resource.adaptTo(InfoSectionModel.class);
    }

    @Test
    void testHeading(){
        assertNotNull(model);
        assertEquals("Hello AEM I Am Heading", model.getHeading());
        assertEquals("Hello AEM I Am Description",model.getDescription());
    }

    /**
     * Tests whether the exported resource type is correct.
     */
    @Test
    void testExportedType() {
        assertEquals(
                "lyca-spa-react/components/content/info-section",
                model.getExportedType()
        );
    }
}