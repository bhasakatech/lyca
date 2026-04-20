package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargeTopUpAmount;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link LycaReacargeTopUpAmountImpl}.
 * <p>
 * This class verifies the functionality of the top-up amount model,
 * ensuring that values are correctly injected from the JSON resource
 * and accessible through getter methods.
 * </p>
 *
 * <p>
 * It uses AEM Mock Context to simulate repository content and adapt
 * resources into Sling Models.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class LycaReacargeTopUpAmountImplTest {

    private final AemContext context = new AemContext();

    private LycaReacargeTopUpAmount model;

    /**
     * Sets up the test environment by loading JSON content
     * and adapting the specific resource to the model.
     */
    @BeforeEach
    void setUp() {
        context.load().json("/lycarecharge.json", "/content/recharge");

        Resource resource = context.resourceResolver()
                .getResource("/content/recharge/topUpAmounts/item0");

        assertNotNull(resource, "Resource should not be null");

        model = resource.adaptTo(LycaReacargeTopUpAmount.class);

        assertNotNull(model, "Model should not be null");
    }

    /**
     * Tests whether the amount value is correctly retrieved.
     */
    @Test
    void testGetAmount() {
        assertEquals("$10.00", model.getAmount());
    }

    /**
     * Tests whether the label value is correctly retrieved.
     */
    @Test
    void testGetLabel() {
        assertEquals("Min Save", model.getLabel());
    }

    /**
     * Tests that both amount and label are not null.
     */
    @Test
    void testNotNullValues() {
        assertNotNull(model.getAmount());
        assertNotNull(model.getLabel());
    }
}
