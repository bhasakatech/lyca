package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.PerfectPlanQuestion;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.Collections;
import java.util.List;

@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {PerfectPlanQuestion.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class PerfectPlanQuestionImpl implements PerfectPlanQuestion {

    @ValueMapValue
    private String question;

    @ValueMapValue(name = "options")
    private List<String> options;

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public List<String> getOptions() {
        if (options == null) {
            return Collections.emptyList();
        }
        return options.size()>4 ? options.subList(0,4) : options;
    }
}