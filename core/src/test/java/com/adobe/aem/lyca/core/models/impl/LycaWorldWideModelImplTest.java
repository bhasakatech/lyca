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

/**
 * Unit test class for {@link LycaWorldWideModelImpl}.
 *
 * This class verifies:
 * - Proper Sling Model adaptation
 * - Mapping of component properties
 * - Handling of multifield (countries list)
 * - Different scenarios like valid, partial, empty, invalid data
 *
 * Uses AEM Mock (AemContext) to simulate repository content.
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaWorldWideModelImplTest {

    /** AEM mock context for simulating repository */
    AemContext context = new AemContext();

    /** Model under test */
    LycaWorldWideModel model;

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
     * Test valid scenario with complete data.
     *
     * Verifies:
     * - All fields are correctly mapped
     * - Multifield countries list is populated
     */
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

    /**
     * Test partial data scenario.
     *
     * Verifies:
     * - Available fields are mapped
     * - Missing fields return null
     */
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

    /**
     * Test scenario where multifield exists but has no items.
     *
     * Verifies:
     * - Countries list is empty but not null
     */
    @Test
    void testEmptyMultifieldScenario() {
        Resource resource = context.resourceResolver().getResource("/content/emptyMultifieldScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNotNull(model.getCountries());
        assertEquals(0, model.getCountries().size());
    }

    /**
     * Test scenario where component has no data.
     *
     * Verifies:
     * - Fields return null
     * - Countries list is null
     */
    @Test
    void testEmptyComponentScenario() {
        Resource resource = context.resourceResolver().getResource("/content/emptyComponentScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNull(model.getWorldwideHeading());
        assertNull(model.getCountries());
    }

    /**
     * Test scenario with invalid data types or values.
     *
     * Verifies:
     * - Values are mapped as-is
     * - Missing nested fields return null
     */
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

    /**
     * Test scenario where multifield structure is incorrect.
     *
     * Verifies:
     * - Countries list is null due to improper structure
     */
    @Test
    void testWrongMultifieldScenario() {
        Resource resource = context.resourceResolver().getResource("/content/wrongMultifieldStructureScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNull(model.getCountries());
    }

    /**
     * Test scenario with a single country entry.
     *
     * Verifies:
     * - Countries list contains exactly one item
     */
    @Test
    void testSingleCountryScenario() {
        Resource resource = context.resourceResolver().getResource("/content/singleCountryScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertNotNull(model);
        assertNotNull(model.getCountries());
        assertEquals(1, model.getCountries().size());

        assertEquals("India", model.getCountries().get(0).getCountryName());
    }

    /**
     * Test to verify exported resource type.
     */
    @Test
    void testExportedType() {
        Resource resource = context.resourceResolver().getResource("/content/validScenario");
        model = resource.adaptTo(LycaWorldWideModel.class);

        assertEquals("lyca-spa-react/components/lycaworldwide", model.getExportedType());
    }
}