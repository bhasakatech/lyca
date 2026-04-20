package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargePlans;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link LycaReacargePlansImpl}.
 * <p>
 * This class verifies that recharge plan properties such as
 * tab key, data, price, and validity are correctly injected
 * from the JSON resource and returned via getter methods.
 * </p>
 *
 * <p>
 * Uses AEM Mock Context to simulate repository structure.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaReacargePlansImplTest {

    private final AemContext context = new AemContext();

    private LycaReacargePlans model;

    /**
     * Loads JSON and adapts a specific plan resource.
     */
    @BeforeEach
    void setUp() {
        context.load().json("/lycarecharge.json", "/content/recharge");

        Resource resource = context.resourceResolver()
                .getResource("/content/recharge/plans/item0");

        assertNotNull(resource, "Resource should not be null");

        model = resource.adaptTo(LycaReacargePlans.class);

        assertNotNull(model, "Model should not be null");
    }

    /**
     * Tests tab key value.
     */
    @Test
    void testGetTabKey() {
        assertEquals("30days", model.getTabKey());
    }

    /**
     * Tests data value.
     */
    @Test
    void testGetData() {
        assertEquals("500MB", model.getData());
    }

    /**
     * Tests price value.
     */
    @Test
    void testGetPrice() {
        assertEquals("$15.00", model.getPrice());
    }

    /**
     * Tests validity value.
     */
    @Test
    void testGetValidity() {
        assertEquals("30 days", model.getValidity());
    }

    /**
     * Tests that all fields are not null.
     */
    @Test
    void testNotNullValues() {
        assertNotNull(model.getTabKey());
        assertNotNull(model.getData());
        assertNotNull(model.getPrice());
        assertNotNull(model.getValidity());
    }
}
