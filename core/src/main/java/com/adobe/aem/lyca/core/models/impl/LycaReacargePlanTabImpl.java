package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargePlanTab;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

/**
 * Implementation class for the {@link LycaReacargePlanTab}.
 * <p>
 * This Sling Model represents a single plan tab used to
 * categorize recharge plans in the UI.
 * </p>
 * <p>
 * Each tab contains a display label and a unique key
 * used to filter and map recharge plans.
 * </p>
 * <p>
 * The properties are injected from the resource using
 * {@link ValueMapValue}.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = LycaReacargePlanTab.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class LycaReacargePlanTabImpl implements LycaReacargePlanTab {

    /** Label displayed for the tab */
    @ValueMapValue
    private String tabLabel;

    /** Unique key used to identify the tab */
    @ValueMapValue
    private String tabKey;

    /** {@inheritDoc} */
    @Override
    public String getTabLabel() {
        return tabLabel;
    }

    /** {@inheritDoc} */
    @Override
    public String getTabKey() {
        return tabKey;
    }
}
