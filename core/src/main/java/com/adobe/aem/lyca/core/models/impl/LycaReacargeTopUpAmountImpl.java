package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargeTopUpAmount;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Implementation class for the {@link LycaReacargeTopUpAmount}.
 * <p>
 * This Sling Model represents a predefined top-up amount option
 * available in the recharge component.
 * </p>
 * <p>
 * Each top-up option includes an amount value and a label
 * used for display in the UI.
 * </p>
 * <p>
 * The properties are injected from the resource using
 * {@link ValueMapValue}.
 * </p>
 * <p>
 * This model is typically used as a child resource within
 * {@link LycaReacargeModelImpl}.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = LycaReacargeTopUpAmount.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class LycaReacargeTopUpAmountImpl implements LycaReacargeTopUpAmount {
    /** Top-up amount value */
    @ValueMapValue
    private String amount;

    /** Label for the top-up amount */
    @ValueMapValue
    private String label;

    /** {@inheritDoc} */
    @Override
    public String getAmount() {
        return amount;
    }

    /** {@inheritDoc} */
    @Override
    public String getLabel() {
        return label;
    }
}
