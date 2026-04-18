package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;

/**
 * Sling Model interface for the Help & Support component.
 *
 * <p>This interface defines all the content fields required to render
 * the Help & Support section, including email support details,
 * support hours, required user information, and call support options.</p>
 *
 * <p>It extends {@link ComponentExporter} to allow JSON export
 * for SPA (Single Page Application) implementations.</p>
 */
public interface HelpSupportModel extends ComponentExporter {

    /**
     * @return main heading of the component
     */
    String getHeading();

    /**
     * @return sub-heading or description text
     */
    String getSubHeading();

    /**
     * @return label for email support section
     */
    String getEmailSupportLabel();

    /**
     * @return response time information
     */
    String getResponseTime();

    /**
     * @return suggestion text for contacting support
     */
    String getSupportSuggestionText();

    /**
     * @return label for support hours section
     */
    String getSupportHoursLabel();

    /**
     * @return list of support hours
     */
    List<String> getSupportHours();

    /**
     * @return label for email field
     */
    String getEmailLabel();

    /**
     * @return support email address
     */
    String getEmail();

    /**
     * @return label for required information section
     */
    String getRequiredInfoLabel();

    /**
     * @return list of required information fields
     */
    List<String> getRequiredInfo();

    /**
     * @return label for users without account info
     */
    String getMentionNoAccountInfoLabel();

    /**
     * @return message for users without account information
     */
    String getMentionNoAccountInfo();

    /**
     * @return additional note text
     */
    String getNote();

    /**
     * @return label for call support section
     */
    String getCallSupportLabel();

    /**
     * @return label for customer care hours
     */
    String getCustomerCareHoursLabel();

    /**
     * @return list of customer care hours
     */
    List<String> getCustomerCareHours();

    /**
     * @return label for call options section
     */
    String getCallOptionsLabel();

    /**
     * @return list of call support options
     */
    List<CallSupportOption> getCallOptions();
}