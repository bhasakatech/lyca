package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargePlanTab;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link LycaReacargePlanTabImpl}.
 * <p>
 * This test verifies that tab label and tab key values
 * are correctly injected from the JSON resource and
 * accessible via getter methods.
 * </p>
 *
 * <p>
 * Uses AEM Mock Context to simulate repository structure.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaReacargePlanTabImplTest {

    private final AemContext context = new AemContext();

    private LycaReacargePlanTab model;

    /**
     * Loads JSON and adapts the specific plan tab resource.
     */
    @BeforeEach
    void setUp() {
        context.load().json("/lycarecharge.json", "/content/recharge");

        Resource resource = context.resourceResolver()
                .getResource("/content/recharge/planTabs/item0");

        assertNotNull(resource, "Resource should not be null");

        model = resource.adaptTo(LycaReacargePlanTab.class);

        assertNotNull(model, "Model should not be null");
    }

    /**
     * Tests tab label value.
     */
    @Test
    void testGetTabLabel() {
        assertEquals("30 days plans", model.getTabLabel());
    }

    /**
     * Tests tab key value.
     */
    @Test
    void testGetTabKey() {
        assertEquals("30days", model.getTabKey());
    }

    /**
     * Tests that values are not null.
     */
    @Test
    void testNotNullValues() {
        assertNotNull(model.getTabLabel());
        assertNotNull(model.getTabKey());
    }
}
