package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaReacargeModel;
import com.adobe.aem.lyca.core.models.LycaReacargePlanTab;
import com.adobe.aem.lyca.core.models.LycaReacargePlans;
import com.adobe.aem.lyca.core.models.LycaReacargeTopUpAmount;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;

/**
 * Implementation class for the {@link LycaReacargeModel}.
 * <p>
 * This Sling Model adapts from {@link Resource} and
 * {@link SlingHttpServletRequest} and provides the complete
 * data structure required for the Lyca Recharge component.
 * </p>
 * <p>
 * The model includes component metadata such as title,
 * description, operator details, recharge plans, top-up options,
 * and payment-related information.
 * </p>
 * <p>
 * Properties are injected using {@link ValueMapValue},
 * and child resources such as plan tabs, plans, and top-up
 * amounts are injected using {@link ChildResource}.
 * </p>
 * <p>
 * This model supports JSON export via
 * {@link ComponentExporter}, enabling integration
 * with SPA frameworks like React.
 * </p>
 *
 * @author Jaya Chandra Reddy
 */
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaReacargeModel.class, ComponentExporter.class},
        resourceType = LycaReacargeModelImpl.RESOURCE_PATH,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class LycaReacargeModelImpl implements LycaReacargeModel {
    /** Resource type for the Lyca Recharge component */
    public static final String RESOURCE_PATH = "lyca-spa-react/components/lycareachargecomponent";

    /** Component title */
    @ValueMapValue
    private String title;

    /** Component description */
    @ValueMapValue
    private String description;

    /** Operator section title */
    @ValueMapValue
    private String operatorTitle;

    /** Country code for phone input */
    @ValueMapValue
    private String countryCode;

    /** Phone input placeholder */
    @ValueMapValue
    private String phoneInputPlaceholder;

    /** Plans section title */
    @ValueMapValue
    private String plansSectionTitle;

    /** List of plan tabs */
    @ChildResource(name = "planTabs")
    private List<LycaReacargePlanTab> planTabs;

    /** List of recharge plans */
    @ChildResource(name = "plans")
    private List<LycaReacargePlans> plans;

    /** Top-up section title */
    @ValueMapValue
    private String topUpTitle;

    /** List of top-up amounts */
    @ChildResource(name = "topUpAmounts")
    private List<LycaReacargeTopUpAmount> topUpAmounts;

    /** Payment section title */
    @ValueMapValue
    private String paymentSectionTitle;

    /** Payment description */
    @ValueMapValue
    private String paymentDescription;

    /** Order summary title */
    @ValueMapValue
    private String orderSummaryTitle;

    /** Regulatory fee label */
    @ValueMapValue
    private String regulatoryFeeLabel;

    /** Regulatory fee percentage */
    @ValueMapValue
    private Double regulatoryFeePercentage;

    /** Total amount label */
    @ValueMapValue
    private String totalAmountLabel;

    /** Terms and conditions text */
    @ValueMapValue
    private String termsText;

    /** CTA button text */
    @ValueMapValue
    private String ctaButtonText;

    /**
     * Returns the exported resource type.
     * @return the resource type
     */
    @Override
    public String getExportedType() {
        return LycaReacargeModelImpl.RESOURCE_PATH;
    }

    /** {@inheritDoc} */
    @Override
    public String getTitle() {
        return title;
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return description;
    }

    /** {@inheritDoc} */
    @Override
    public String getOperatorTitle() {
        return operatorTitle;
    }

    /** {@inheritDoc} */
    @Override
    public String getCountryCode() {
        return countryCode;
    }

    /** {@inheritDoc} */
    @Override
    public String getPhoneInputPlaceholder() {
        return phoneInputPlaceholder;
    }

    /** {@inheritDoc} */
    @Override
    public String getPlansSectionTitle() {
        return plansSectionTitle;
    }

    /** {@inheritDoc} */
    @Override
    public List<LycaReacargePlanTab> getPlanTabs() {
        return planTabs;
    }

    /** {@inheritDoc} */
    @Override
    public List<LycaReacargePlans> getPlans() {
        return plans;
    }

    /** {@inheritDoc} */
    @Override
    public String getTopUpTitle() {
        return topUpTitle;
    }

    /** {@inheritDoc} */
    @Override
    public List<LycaReacargeTopUpAmount> getTopUpAmounts() {
        return topUpAmounts;
    }

    /** {@inheritDoc} */
    @Override
    public String getPaymentSectionTitle() {
        return paymentSectionTitle;
    }

    /** {@inheritDoc} */
    @Override
    public String getPaymentDescription() {
        return paymentDescription;
    }

    /** {@inheritDoc} */
    @Override
    public String getOrderSummaryTitle() {
        return orderSummaryTitle;
    }

    /** {@inheritDoc} */
    @Override
    public String getRegulatoryFeeLabel() {
        return regulatoryFeeLabel;
    }

    /** {@inheritDoc} */
    @Override
    public Double getRegulatoryFeePercentage() {
        return regulatoryFeePercentage;
    }

    /** {@inheritDoc} */
    @Override
    public String getTotalAmountLabel() {
        return totalAmountLabel;
    }

    /** {@inheritDoc} */
    @Override
    public String getTermsText() {
        return termsText;
    }

    /** {@inheritDoc} */
    @Override
    public String getCtaButtonText() {
        return ctaButtonText;
    }
}
