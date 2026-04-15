package com.adobe.aem.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
/**
 * Sling Model representing a Price Plan Content Fragment.
 *
 * <p>
 * This model adapts from a {@link Resource} and maps the properties
 * of a price plan stored in the repository (e.g., Content Fragment or node).
 * It is used to read authored data and expose it to backend services
 * or other models.
 * </p>
 */
@Model(adaptables = Resource.class,
            defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PricePlanCFModel {
    /** Title of the plan */
    @ValueMapValue
    private String planTitle;

    /** Monthly price of the plan */
    @ValueMapValue
    private double priceMonthly;

    /** Yearly price of the plan */
    @ValueMapValue
    private double priceYearly;

    /** Data limit associated with the plan */
    @ValueMapValue
    private String dataLimit;

    /** Features included in the plan */
    @ValueMapValue
    private String[] features;

    /** Indicates whether the plan is marked as popular */
    @ValueMapValue
    private boolean isPopular;
    /** Button label */
    @ValueMapValue
    private String ctaLabel;
    /** Button link */
    @ValueMapValue
    private String ctaLink;
    /**
     * @return the plan title
     */
    public String getPlanTitle() {
        return planTitle;
        }
    /**
     * @return the plan monthly price
     */
    public double getPriceMonthly() {
        return priceMonthly;
        }
    /**
     * @return the plan yearly price
     */
    public double getPriceYearly() {
        return priceYearly;
        }
    /**
     * @return the plan data limit
     */
    public String getDataLimit() {
        return dataLimit;
        }
    /**
     * @return the plan features
     */
    public String[] getFeatures() {
        return features;
        }
    /**
     * @return the plan is populer or not
     */
    public boolean isPopular() {
        return isPopular;
        }
    /**
     * @return the button label
     */
    public String getCtaLabel() {
        return ctaLabel;
        }
    /**
     * @return the button link
     */
    public String getCtaLink() {
        return ctaLink;
        }
}
