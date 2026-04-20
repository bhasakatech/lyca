package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.HelpSupportModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for {@link HelpSupportModelImpl}.
 *
 * <p>
 * This test validates:
 * <ul>
 *     <li>Basic field mappings</li>
 *     <li>Email support related properties</li>
 *     <li>Required information fields</li>
 *     <li>Call support and call options</li>
 *     <li>JSON export type</li>
 * </ul>
 *
 * <p>
 * Uses AEM Mock framework to simulate repository and Sling Models adaptation.
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class HelpSupportModelImplTest {

    /**
     * AEM Mock context used to simulate JCR repository and Sling environment.
     */
    private final AemContext context = new AemContext();

    /**
     * Instance of the model under test.
     */
    private HelpSupportModel helpSupportModel;

    /**
     * Initializes test context before each test execution.
     *
     * <p>
     * Steps:
     * <ul>
     *     <li>Registers Sling Models</li>
     *     <li>Loads test JSON into mock repository</li>
     *     <li>Adapts resource to model</li>
     * </ul>
     */
    @BeforeEach
    void init() {
        context.addModelsForClasses(HelpSupportModelImpl.class, CallSupportOptionImpl.class);
        context.load().json("/helpandsupport.json", "/content/test");
        Resource resource = context.resourceResolver().getResource("/content/test");
        assertNotNull(resource);
        helpSupportModel = resource.adaptTo(HelpSupportModel.class);
        assertNotNull(helpSupportModel);
    }

    /**
     * Tests basic component fields such as heading and subheading.
     */
    @Test
    void testBasicFields() {
        assertEquals("Help & Support", helpSupportModel.getHeading());
        assertEquals("We are here to assist you", helpSupportModel.getSubHeading());
    }

    /**
     * Tests email support related fields including:
     * <ul>
     *     <li>Email label and value</li>
     *     <li>Response time</li>
     *     <li>Support hours</li>
     *     <li>Additional descriptive fields</li>
     * </ul>
     */
    @Test
    void testEmailSupportFields() {
        assertEquals("Email Support", helpSupportModel.getEmailSupportLabel());
        assertEquals("Within 24 hours", helpSupportModel.getResponseTime());
        assertEquals("support@lycamobile.com", helpSupportModel.getEmail());

        // Validate support hours list
        assertNotNull(helpSupportModel.getSupportHours());
        assertFalse(helpSupportModel.getSupportHours().isEmpty());

        // Validate additional fields for coverage
        assertEquals("Include all details", helpSupportModel.getSupportSuggestionText());
        assertEquals("Support Hours", helpSupportModel.getSupportHoursLabel());
        assertEquals("Email", helpSupportModel.getEmailLabel());
    }

    /**
     * Tests required information fields provided for support requests.
     */
    @Test
    void testRequiredInfo() {
        assertNotNull(helpSupportModel.getRequiredInfo());
        assertEquals(2, helpSupportModel.getRequiredInfo().size());

        // Validate label for required information
        assertEquals("Required Info", helpSupportModel.getRequiredInfoLabel());
    }

    /**
     * Tests call support related fields including:
     * <ul>
     *     <li>Call support label</li>
     *     <li>Customer care hours</li>
     *     <li>Additional informational notes</li>
     * </ul>
     */
    @Test
    void testCallSupport() {
        assertEquals("Call Support", helpSupportModel.getCallSupportLabel());
        assertEquals("customer Care Hours",helpSupportModel.getCustomerCareHoursLabel());
        // Validate customer care hours
        assertNotNull(helpSupportModel.getCustomerCareHours());
        assertFalse(helpSupportModel.getCustomerCareHours().isEmpty());

        // Validate additional informational fields
        assertEquals("Note", helpSupportModel.getMentionNoAccountInfoLabel());
        assertEquals("Do not share OTP", helpSupportModel.getMentionNoAccountInfo());
        assertEquals("We will respond soon", helpSupportModel.getNote());
        assertEquals("Options", helpSupportModel.getCallOptionsLabel());
    }

    /**
     * Tests call support options including label and phone number.
     */
    @Test
    void testCallOptions() {
        assertNotNull(helpSupportModel.getCallOptions());
        assertEquals(2, helpSupportModel.getCallOptions().size());

        // Validate first call option details
        assertEquals("Customer Care",
                helpSupportModel.getCallOptions().get(0).getLabel());

        assertEquals("1800-123-456",
                helpSupportModel.getCallOptions().get(0).getNumber());
    }

    /**
     * Tests exported resource type used in JSON exporter.
     */
    @Test
    void testExportedType() {
        assertEquals(
                "lyca-spa-react/components/helpandsupport",
                helpSupportModel.getExportedType()
        );
    }
}