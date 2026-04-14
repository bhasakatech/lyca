package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaCtaButton;
import com.adobe.aem.lyca.core.models.LycaCtaItemModel;
import com.adobe.aem.lyca.core.models.LycaJoinModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.Resource;
import java.util.List;

/**
 * Sling Model implementation for the Lyca Join component.
 *
 * <p>
 * This model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and exposes component data via {@link ComponentExporter} for SPA (React) consumption.
 * </p>
 *
 * <p>
 * The model retrieves authored content from the AEM component dialog using:
 * </p>
 * <ul>
 *     <li>{@link ValueMapValue} for simple properties</li>
 *     <li>{@link ChildResource} for multifield/nested resources</li>
 * </ul>
 *
 * <p>
 * The exported JSON is consumed by the frontend to render dynamic UI elements
 * such as titles, descriptions, CTA items, buttons, and form-related fields.
 * </p>
 *
 * @since 1.0
 */
@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaJoinModel.class, ComponentExporter.class},
        resourceType = LycaJoinModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class LycaJoinModelImpl implements LycaJoinModel {

    /**
     * Resource type for the Lyca Join component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/joinLyca";

    /** Component title authored in dialog */
    @ValueMapValue
    private String title;

    /** Component description authored in dialog */
    @ValueMapValue
    private String description;

    /** Multifield CTA items (icon, label, link) */
    @ChildResource(name = "ctaItems")
    private List<LycaCtaItemModel> ctaItems;

    /** Heading text for the form section */
    @ValueMapValue
    private String heading;

    /** Multifield CTA buttons */
    @ChildResource(name = "ctas")
    private List<LycaCtaButton> ctaButtons;

    /** Input field placeholder text */
    @ValueMapValue
    private String placeholder;

    /** Submit button label */
    @ValueMapValue
    private String submitText;

    /** Submit button redirection link */
    @ValueMapValue
    private String submitLink;

    /** Promotional text displayed in the component */
    @ValueMapValue
    private String promotionText;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LycaCtaItemModel> getCtaItems() {
        return ctaItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LycaCtaButton> getCtas() {
        return ctaButtons;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubmitText() {
        return submitText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubmitLink() {
        return submitLink;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPromotionText() {
        return promotionText;
    }

    /**
     * Returns the resource type of the component for JSON export.
     *
     * @return resource type string
     */
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }
}