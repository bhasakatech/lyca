package com.adobe.aem.lyca.core.models.impl;

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
class LycaWorldWideModelImplTest {
        AemContext context=new AemContext();
        LycaWorldWideModel model;
        @BeforeEach
        void init(){
            context.addModelsForClasses(LycaWorldWideModelImpl.class, CountryModelImpl.class);
            context.load().json("/LycaWorldWide.json", "/content");
        }
    @Test
    void testValidScenario() {
        Resource resource = context.resourceResolver().getResource("/content/validScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertEquals("Stay Connected Worldwide", model.getWorldwideHeading());
        assertEquals("Cheap international calls for everyone", model.getWorldwideSubHeading());
        assertEquals("Search for a country...", model.getSearchPlaceholderText());

        assertNotNull(model.getCountries());
        assertEquals(2, model.getCountries().size());

        assertEquals("Germany", model.getCountries().get(0).getCountryName());
        assertEquals("India", model.getCountries().get(1).getCountryName());

        assertEquals("View More", model.getCtaLabel());
        assertEquals("/content/view-more", model.getCtaLink());
    }
    @Test
    void testPartialScenario() {
        Resource resource = context.resourceResolver().getResource("/content/partialScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertEquals("Stay Connected Worldwide", model.getWorldwideHeading());

        assertNotNull(model.getCountries());
        assertEquals(1, model.getCountries().size());

        assertNull(model.getCtaLabel());
    }
    @Test
    void testEmptyMultifieldScenario() {
        Resource resource = context.resourceResolver().getResource("/content/emptyMultifieldScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNotNull(model.getCountries());
        assertEquals(0, model.getCountries().size());
    }
    @Test
    void testEmptyComponentScenario() {
        Resource resource = context.resourceResolver().getResource("/content/emptyComponentScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNull(model.getWorldwideHeading());
        assertNull(model.getCountries());
    }
    @Test
    void testInvalidScenario() {
        Resource resource = context.resourceResolver().getResource("/content/invalidDataScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertEquals("123", model.getWorldwideHeading());

        assertNotNull(model.getCountries());
        assertEquals(1, model.getCountries().size());

        assertNull(model.getCountries().get(0).getCountryName());
    }
    @Test
    void testWrongMultifieldScenario() {
        Resource resource = context.resourceResolver().getResource("/content/wrongMultifieldStructureScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);
        assertNotNull(model);
        assertNull(model.getCountries());
    }
    @Test
    void testSingleCountryScenario() {
        Resource resource = context.resourceResolver().getResource("/content/singleCountryScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNotNull(model.getCountries());
        assertEquals(1, model.getCountries().size());

        assertEquals("India", model.getCountries().get(0).getCountryName());
    }
    @Test
    void testExportedType() {
        Resource resource = context.resourceResolver().getResource("/content/validScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);
        assertEquals("lyca-spa-react/components/lycaworldwide", model.getExportedType());
    }
}