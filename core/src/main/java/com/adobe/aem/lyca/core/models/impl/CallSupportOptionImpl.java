package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.CallSupportOption;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
/**
 * Implementation of {@link CallSupportOption} Sling Model.
 *
 * <p>This model represents a single call support option configured
 * as a child resource in the Help & Support component.</p>
 *
 * <p>Each option contains a label (description) and a phone number
 * that can be displayed in the UI.</p>
 */
@Model(
        adaptables = Resource.class,
        adapters = CallSupportOption.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CallSupportOptionImpl implements CallSupportOption {

    /**
     * Label describing the call support option
     * (e.g., "Customer Care", "Technical Support").
     */
    @ValueMapValue
    private String label;

    /**
     * Phone number associated with the support option.
     */
    @ValueMapValue
    private String number;

    /**
     * Returns the label of the call support option.
     *
     * @return label text
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Returns the phone number of the call support option.
     *
     * @return phone number
     */
    @Override
    public String getNumber() {
        return number;
    }
}