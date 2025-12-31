package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FindPerfectPlanModel {

    @ValueMapValue
    private String heading;
    @ValueMapValue
    private String resultHeading;
    @ValueMapValue
    private String resultSubtext;
    @ValueMapValue
    private String recommendedPlanName;
    @ValueMapValue
    private String recommendedPlanData;
    @ValueMapValue
    private String recommendedPlanPrice;
    @ValueMapValue
    private String primaryCTALabel;
    @ValueMapValue
    private String primaryCTALink;
    @ValueMapValue
    private String secondaryCTALabel;
    @ValueMapValue
    private String secondaryCTALink;
    @ChildResource(name = "questions")
    private List<QuestionItemModel> questions;
}
