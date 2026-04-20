package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

/**
 * Represents the main model interface for the Lyca Recharge component.
 * <p>
 * This interface provides methods to retrieve all data required
 * for rendering the recharge UI including titles, descriptions,
 * operator details, plan information, top-up options, and payment details.
 * </p>
 * <p>
 * It acts as a structured contract between the backend Sling Model
 * and frontend applications such as React.
 * </p>
 * <p>
 * It extends {@link ComponentExporter} to support JSON export
 * for SPA-based frontend integrations.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
public interface LycaReacargeModel extends ComponentExporter {
    /**
     * Returns the main title of the recharge component.
     * @return the title string
     */
    String getTitle();

    /**
     * Returns the description of the recharge component.
     * @return the description text
     */
    String getDescription();

    /**
     * Returns the operator section title.
     * @return the operator title
     */
    String getOperatorTitle();

    /**
     * Returns the country code for the phone input.
     * @return the country code
     */
    String getCountryCode();

    /**
     * Returns the placeholder text for the phone input field.
     * @return the placeholder text
     */
    String getPhoneInputPlaceholder();

    /**
     * Returns the title for the plans section.
     * @return the plans section title
     */
    String getPlansSectionTitle();

    /**
     * Returns the list of plan tabs.
     * @return list of {@link LycaReacargePlanTab}
     */
    List<LycaReacargePlanTab> getPlanTabs();

    /**
     * Returns the list of recharge plans.
     * @return list of {@link LycaReacargePlans}
     */
    List<LycaReacargePlans> getPlans();

    /**
     * Returns the title for the top-up section.
     * @return the top-up title
     */
    String getTopUpTitle();

    /**
     * Returns the list of top-up amounts.
     * @return list of {@link LycaReacargeTopUpAmount}
     */
    List<LycaReacargeTopUpAmount> getTopUpAmounts();

    /**
     * Returns the payment section title.
     * @return the payment section title
     */
    String getPaymentSectionTitle();

    /**
     * Returns the payment description text.
     * @return the payment description
     */
    String getPaymentDescription();

    /**
     * Returns the order summary title.
     * @return the order summary title
     */
    public String getOrderSummaryTitle();

    /**
     * Returns the label for regulatory fee.
     * @return the regulatory fee label
     */
    String getRegulatoryFeeLabel();

    /**
     * Returns the regulatory fee percentage.
     * @return the regulatory fee percentage
     */
    Double getRegulatoryFeePercentage();

    /**
     * Returns the label for total amount.
     * @return the total amount label
     */
    String getTotalAmountLabel();

    /**
     * Returns the terms and conditions text.
     * @return the terms text
     */
    String getTermsText();

    /**
     * Returns the CTA (Call-To-Action) button text.
     * @return the button text
     */
    String getCtaButtonText();
}
