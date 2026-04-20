package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaFAQModelInterface;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;


/**
 * Sling Model implementation for the Lyca FAQ Component.
 *
 * <p>This model adapts from {@link Resource} and {@link SlingHttpServletRequest}
 * and provides data required to render the FAQ component in AEM.</p>
 *
 * <p>The model retrieves:</p>
 * <ul>
 *     <li>Main heading of the FAQ section</li>
 *     <li>List of FAQ items (question and answer pairs)</li>
 * </ul>
 *
 * <p>The FAQ items are authored as a multifield in the dialog and are mapped
 * using {@link ChildResource}.</p>
 *
 * <p>This model also implements {@link ComponentExporter}, enabling it to be
 * exported as JSON via the Sling Model Exporter (used in SPA frameworks).</p>
 *
 * <p>Resource type associated with this component:
 * {@value #RESOURCE_TYPE}</p>
 */

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        adapters = {LycaFAQModelInterface.class, ComponentExporter.class},
        resourceType = LycaFAQComponentModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LycaFAQComponentModelImpl implements LycaFAQModelInterface {


    /**
     * Resource type for the Lyca FAQ component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycaFAQComponent";

    /**
     * Main heading of the FAQ section authored in the component.
     */
    @ValueMapValue
    public String faqMainHeading;

    /**
     * List of FAQ items authored via multifield.
     * Each item contains a question and answer.
     */
    @ChildResource(name="faq")
    public List<LycaFAQs> items;

    /**
     * Returns the exported resource type of the component.
     *
     * @return the resource type string
     */
    @Override
    public String getExportedType() {
        return LycaFAQComponentModelImpl.RESOURCE_TYPE;
    }

    /**
     * Returns the main heading of the FAQ section.
     *
     * @return the FAQ main heading, or {@code null} if not authored
     */
    @Override
    public String getMainHeading() {
        return faqMainHeading;
    }

    /**
     * Returns the list of FAQ items.
     *
     * @return list of {@link LycaFAQs} objects, or {@code null} if no items are authored
     */
    @Override
    public List<LycaFAQs> getFAQs() {
        return items;
    }
}
