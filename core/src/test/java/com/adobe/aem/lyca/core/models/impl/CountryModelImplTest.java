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

/**
 * Unit test class for {@link CountryModelImpl}.
 *
 * This class verifies:
 * - Sling Model adaptation for CountryModel
 * - Mapping of country fields (name, logo, link)
 * - Handling of valid, partial, empty, and invalid data scenarios
 *
 * Uses AEM Mock (AemContext) to simulate repository content.
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class CountryModelImplTest {

    /** AEM mock context to simulate repository */
    AemContext context = new AemContext();

    /** Model under test */
    CountryModel model;

    /**
     * Setup method executed before each test.
     *
     * - Registers required Sling Models
     * - Loads JSON test data into mock repository
     */
    @BeforeEach
    void init() {
        context.addModelsForClasses(LycaWorldWideModelImpl.class, CountryModelImpl.class);
        context.load().json("/LycaWorldWide.json", "/content");
    }

    /**
     * Test valid country data mapping.
     *
     * Verifies:
     * - All fields are correctly mapped
     */
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

    /**
     * Test second country in multifield.
     *
     * Verifies:
     * - Multiple country entries are handled correctly
     */
    @Test
    void testSecondCountry() {
        Resource resource = context.resourceResolver()
                .getResource("/content/validScenario/countries/item1");

        CountryModel model = resource.adaptTo(CountryModel.class);

        assertNotNull(model);
        assertEquals("India", model.getCountryName());
    }

    /**
     * Test partial country data.
     *
     * Verifies:
     * - Available fields are mapped
     * - Missing fields return null
     */
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

    /**
     * Test empty country resource.
     *
     * Verifies:
     * - Model is created
     * - All fields return null
     */
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

    /**
     * Test invalid data scenario.
     *
     * Verifies:
     * - Non-string values are converted to string where applicable
     * - Null values remain null
     */
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