package com.adobe.aem.lyca.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**

 * PrepaidSimPlanItem class
 *
 * <p>
 * This class represents a single Prepaid SIM Plan item.
 * It contains all the details required to display a plan such as
 * basic information, pricing, features, CTA buttons, and additional metadata.
 * </p>

 */
public class PrepaidSimPlanItem {
    /**
     * Returns the name/identifier of the plan.
     */
    private String name;

    /**
     * Returns the label of the plan.
     */
    private String planLabel;

    /**
     * Returns the main title of the plan (e.g., "Data").
     */
    private String planTitle;

    /**
     * Returns the subtitle or additional text under the title.
     */
    private String subTitle;

    /**
     * Returns the category of the plan (used for filtering/toggles).
     */
    private String category;

    /**
     * Returns the price of the plan.
     */
    private String price;

    /**
     * Returns the validity period of the plan.
     */
    private String validity;

    /**
     * Returns the list of features associated with the plan.
     */
    private List<String> features;

    /**
     * Returns the label for the "Buy Now" button.
     */
    private String buyNowCtaLabel;

    /**
     * Returns the link for the "Buy Now" action.
     */
    private String buyNowCtaLink;

    /**
     * Returns the label for the "Add to Basket" button.
     */
    private String addToBasketCtaLabel;

    /**
     * Returns the link for the "Add to Basket" action.
     */
    private String addToBasketCtaLink;

    /**
     * Returns the label for the "View More" link.
     */
    private String viewMoreLabel;

    /**
     * Returns the link for the "View More" action.
     */
    private String viewMoreLink;

    /**
     * Returns the footer text displayed at the bottom of the plan card.
     */
    private String footerText;

    /**
     * Returns the Content Fragment path associated with the plan.
     */
    private String path;

    /**
     * Returns the image associated with the plan.
     */
    private String image;

    /**
     * Returns the sim-image associated with the plan features.
     */
    private String simImage;

    /**
     * Returns the network-image associated with the plan features.
     */
    private String networkImage;


}
