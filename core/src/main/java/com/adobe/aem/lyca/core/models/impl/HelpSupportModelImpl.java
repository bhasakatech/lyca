package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.CallSupportOption;
import com.adobe.aem.lyca.core.models.HelpSupportModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link HelpSupportModel} for the Help and Support component.
 *
 * <p>This Sling Model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and exports component data as JSON using Sling Model Exporter.</p>
 *
 * <p>The model provides all content required to render the Help & Support section,
 * including email support details, support hours, required information, and
 * call support options.</p>
 */
@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {HelpSupportModel.class, ComponentExporter.class},
        resourceType = HelpSupportModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class HelpSupportModelImpl implements HelpSupportModel {

    /**
     * Resource type for the Help and Support component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/helpandsupport";

    /** Component heading */
    @ValueMapValue
    private String heading;

    /** Component sub-heading */
    @ValueMapValue
    private String subHeading;

    /** Label for email support section */
    @ValueMapValue
    private String emailSupportLabel;

    /** Response time text */
    @ValueMapValue
    private String responseTime;

    /** Suggestion text for support */
    @ValueMapValue
    private String supportSuggestionText;

    /** Label for support hours */
    @ValueMapValue
    private String supportHoursLabel;

    /** List of support hours */
    @ValueMapValue
    private List<String> supportHours;

    /** Label for email field */
    @ValueMapValue
    private String emailLabel;

    /** Support email address */
    @ValueMapValue
    private String email;

    /** Label for required information */
    @ValueMapValue
    private String requiredInfoLabel;

    /** List of required information fields */
    @ValueMapValue
    private List<String> requiredInfo;

    /** Label for no account information */
    @ValueMapValue
    private String mentionNoAccountInfoLabel;

    /** Message for users without account information */
    @ValueMapValue
    private String mentionNoAccountInfo;

    /** Additional note text */
    @ValueMapValue
    private String note;

    /** Label for call support section */
    @ValueMapValue
    private String callSupportLabel;

    /** Label for customer care hours */
    @ValueMapValue
    private String customerCareHoursLabel;

    /** List of customer care hours */
    @ValueMapValue
    private List<String> customerCareHours;

    /** Label for call options */
    @ValueMapValue
    private String callOptionsLabel;

    /**
     * List of call support options configured as child resources.
     */
    @ChildResource(name = "callOptions")
    private List<CallSupportOption> callOptions;

    /**
     * @return component heading
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     * @return component sub-heading
     */
    @Override
    public String getSubHeading() {
        return subHeading;
    }

    /**
     * @return email support label
     */
    @Override
    public String getEmailSupportLabel() {
        return emailSupportLabel;
    }

    /**
     * @return response time text
     */
    @Override
    public String getResponseTime() {
        return responseTime;
    }

    /**
     * @return support suggestion text
     */
    @Override
    public String getSupportSuggestionText() {
        return supportSuggestionText;
    }

    /**
     * @return support hours label
     */
    @Override
    public String getSupportHoursLabel() {
        return supportHoursLabel;
    }

    /**
     * @return list of support hours or empty list if null
     */
    @Override
    public List<String> getSupportHours() {
        return supportHours != null ? supportHours : Collections.emptyList();
    }

    /**
     * @return email label
     */
    @Override
    public String getEmailLabel() {
        return emailLabel;
    }

    /**
     * @return support email address
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @return required info label
     */
    @Override
    public String getRequiredInfoLabel() {
        return requiredInfoLabel;
    }

    /**
     * @return list of required info fields or empty list if null
     */
    @Override
    public List<String> getRequiredInfo() {
        return requiredInfo != null ? requiredInfo : Collections.emptyList();
    }

    /**
     * @return label for no account info
     */
    @Override
    public String getMentionNoAccountInfoLabel() {
        return mentionNoAccountInfoLabel;
    }

    /**
     * @return message for users without account info
     */
    @Override
    public String getMentionNoAccountInfo() {
        return mentionNoAccountInfo;
    }

    /**
     * @return additional note text
     */
    @Override
    public String getNote() {
        return note;
    }

    /**
     * @return call support label
     */
    @Override
    public String getCallSupportLabel() {
        return callSupportLabel;
    }

    /**
     * @return customer care hours label
     */
    @Override
    public String getCustomerCareHoursLabel() {
        return customerCareHoursLabel;
    }

    /**
     * @return list of customer care hours or empty list if null
     */
    @Override
    public List<String> getCustomerCareHours() {
        return customerCareHours != null ? customerCareHours : Collections.emptyList();
    }

    /**
     * @return call options label
     */
    @Override
    public String getCallOptionsLabel() {
        return callOptionsLabel;
    }

    /**
     * @return list of call support options or empty list if null
     */
    @Override
    public List<CallSupportOption> getCallOptions() {
        return callOptions != null ? callOptions : Collections.emptyList();
    }

    /**
     * Returns the exported resource type for Sling Model Exporter.
     *
     * @return resource type
     */
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}