package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PerfectPlanQuestion;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.Collections;
import java.util.List;

/**
 * Sling Model implementation for {@link PerfectPlanQuestion}.
 *
 * <p>
 * This model represents a single question along with its selectable options
 * used in the Perfect Plan component.
 * </p>
 *
 * <p>
 * The model adapts from {@link SlingHttpServletRequest} and {@link Resource}
 * and reads values configured in the AEM component dialog.
 * </p>
 *
 * <h3>Responsibilities:</h3>
 * <ul>
 *     <li>Expose question text</li>
 *     <li>Provide a safe list of options</li>
 *     <li>Limit the number of options to a maximum of 4</li>
 * </ul>
 */
@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {PerfectPlanQuestion.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PerfectPlanQuestionImpl implements PerfectPlanQuestion {

    /**
     * The question text configured in the dialog.
     */
    @ValueMapValue
    private String question;

    /**
     * List of options associated with the question.
     * <p>
     * This is read from the "options" property in the resource.
     * </p>
     */
    @ValueMapValue(name = "options")
    private List<String> options;

    /**
     * Returns the question text.
     */
    @Override
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the list of options for the question.
     *
     * <p>
     * Behavior:
     * <ul>
     *     <li>Returns an empty list if options are null</li>
     *     <li>Limits the number of options to a maximum of 4</li>
     * </ul>
     * </p>
     */
    @Override
    public List<String> getOptions() {
        if (options == null) {
            return Collections.emptyList();
        }
        return options.size()>4 ? options.subList(0,4) : options;
    }
}