package com.adobe.aem.lyca.core.models;

import com.adobe.aem.lyca.core.models.impl.LycaFAQs;
import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;


/**
 * Interface for the Lyca FAQ Component Sling Model.
 *
 * <p>This interface defines the contract for retrieving FAQ-related data
 * to be used in rendering the component in AEM and exporting it as JSON
 * for SPA applications.</p>
 *
 * <p>Implementing classes should provide:</p>
 * <ul>
 *     <li>Main heading of the FAQ section</li>
 *     <li>List of FAQ items (question and answer pairs)</li>
 * </ul>
 *
 * <p>This interface extends {@link ComponentExporter}, enabling the model
 * to be serialized into JSON using the Sling Model Exporter framework.</p>
 */
public interface LycaFAQModelInterface extends ComponentExporter {

    /**
     * Returns the main heading of the FAQ component.
     *
     * @return the FAQ main heading, or {@code null} if not authored
     */

    public String getMainHeading();


    /**
     * Returns the list of FAQ items.
     *
     * @return a list of {@link LycaFAQs} objects representing FAQ entries,
     *         or {@code null} if no FAQs are available
     */
    public List<LycaFAQs> getFAQs();

}
