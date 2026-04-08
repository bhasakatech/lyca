package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.CountryModel;
import com.adobe.aem.lyca.core.models.LycaWorldWideModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class CountryModelImplTest {
    AemContext context=new AemContext();
    CountryModel model;
    @BeforeEach
    void init(){
        context.addModelsForClasses(LycaWorldWideModelImpl.class, CountryModelImpl.class);
        context.load().json("/LycaWorldWide.json", "/content");
    }
    @Test
    void testValidCountry() {
        Resource resource = context.resourceResolver()
                .getResource("/content/validScenario/countries/item0");

        model = resource.adaptTo(CountryModel.class);

        assertNotNull(model);
        assertEquals("Germany", model.getCountryName());
        assertEquals("/content/dam/flags/germany.png", model.getCountryLogo());
        assertEquals("/content/germany", model.getLogoLink());
    }
    @Test
    void testSecondCountry() {
        Resource resource = context.resourceResolver()
                .getResource("/content/validScenario/countries/item1");

        CountryModel model = resource.adaptTo(CountryModel.class);

        assertNotNull(model);
        assertEquals("India", model.getCountryName());
    }
    @Test
    void testPartialCountry() {
        Resource resource = context.resourceResolver()
                .getResource("/content/partialScenario/countries/item0");

        CountryModel model = resource.adaptTo(CountryModel.class);

        assertNotNull(model);
        assertEquals("India", model.getCountryName());
        assertNull(model.getCountryLogo());
        assertNull(model.getLogoLink());
    }
    @Test
    void testEmptyCountry() {
        context.create().resource("/content/emptyCountry");

        Resource resource = context.resourceResolver()
                .getResource("/content/emptyCountry");

        CountryModel model = resource.adaptTo(CountryModel.class);

        assertNotNull(model);
        assertNull(model.getCountryName());
        assertNull(model.getCountryLogo());
        assertNull(model.getLogoLink());
    }
    @Test
    void testInvalidCountry() {
        Resource resource = context.resourceResolver()
                .getResource("/content/invalidDataScenario/countries/item0");

        CountryModel model = resource.adaptTo(CountryModel.class);

        assertNotNull(model);

        // number → converted to string
        assertEquals("456", model.getCountryLogo());

        // null stays null
        assertNull(model.getCountryName());
    }
}