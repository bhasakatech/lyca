package com.adobe.aem.lyca.core.models.impl;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Sling Model representing a single FAQ item.
 *
 * <p>This model is adaptable from both {@link Resource} and
 * {@link SlingHttpServletRequest} and is used to map FAQ data
 * authored in AEM dialogs.</p>
 *
 * <p>Each FAQ item consists of a question and its corresponding answer,
 * typically authored as part of a multifield in a parent component.</p>
 *
 * <p>The model is also configured as a JSON exporter using the Sling Model
 * Exporter framework, enabling it to be serialized into JSON format
 * (e.g., for SPA applications).</p>
 */

@Model(
        adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name= ExporterConstants.SLING_MODEL_EXPORTER_NAME,extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LycaFAQs {
    /**
     * The FAQ question authored in the component.
     */
    @ValueMapValue
    private String question;

    /**
     * The FAQ answer authored in the component.
     * This may contain HTML content.
     */
    @ValueMapValue
    private String ans;

    /**
     * Returns the FAQ question.
     *
     * @return the question text, or {@code null} if not authored
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the FAQ answer.
     *
     * @return the answer text (may contain HTML), or {@code null} if not authored
     */
    public String getAns() {
        return ans;
    }
}

