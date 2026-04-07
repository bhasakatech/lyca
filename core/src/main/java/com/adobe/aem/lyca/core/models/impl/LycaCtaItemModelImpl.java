package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaCtaItemModel;
import lombok.Getter;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = org.apache.sling.api.resource.Resource.class,
        adapters = LycaCtaItemModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Getter
public class LycaCtaItemModelImpl implements LycaCtaItemModel {

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String link;
}