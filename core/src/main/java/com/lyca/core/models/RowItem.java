package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RowItem {

    @ValueMapValue
    private String label;

    @ChildResource(name = "values")
    private List<Resource> values;

    public String getLabel() {
        return label;
    }

    public List<Resource> getValues() {
        return values;
    }
}

