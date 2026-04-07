package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaCtaButton;
import lombok.Getter;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = org.apache.sling.api.resource.Resource.class,
        adapters = LycaCtaButton.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Getter
public class LycaCtaButtonImpl implements LycaCtaButton {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String link;
}
