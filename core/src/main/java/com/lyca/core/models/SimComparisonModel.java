package com.lyca.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SimComparisonModel {

    @ValueMapValue
    private String heading;
    @ValueMapValue
    private String addMoreSimLabel;
    @ValueMapValue
    private String addMoreSimLink;

    @ChildResource(name = "columns")
    private List<ColumnItem> columns;

    @ChildResource(name = "rows")
    private List<RowItem> rows;
}
