package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargePlans;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

/**
 * Implementation class for the {@link LycaReacargePlans}.
 * <p>
 * This Sling Model represents an individual recharge plan
 * and provides details such as tab association, data benefits,
 * pricing, and validity.
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
        adapters = LycaReacargePlans.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class LycaReacargePlansImpl implements LycaReacargePlans {
    /** Tab key used to associate the plan with a specific tab */
    @ValueMapValue
    private String tabKey;

    /** Data benefits of the recharge plan */
    @ValueMapValue
    private String data;

    /** Price of the recharge plan */
    @ValueMapValue
    private String price;

    /** Validity period of the recharge plan */
    @ValueMapValue
    private String validity;

    /** {@inheritDoc} */
    @Override
    public String getTabKey() {
        return tabKey;
    }

    /** {@inheritDoc} */
    @Override
    public String getData() {
        return data;
    }

    /** {@inheritDoc} */
    @Override
    public String getPrice() {
        return price;
    }

    /** {@inheritDoc} */
    @Override
    public String getValidity() {
        return validity;
    }
}
