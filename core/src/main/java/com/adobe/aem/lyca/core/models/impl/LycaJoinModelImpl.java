package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.LycaCtaButton;
import com.adobe.aem.lyca.core.models.LycaCtaItemModel;
import com.adobe.aem.lyca.core.models.LycaJoinModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.Resource;
import java.util.List;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},
        adapters = {LycaJoinModel.class, ComponentExporter.class},
        resourceType = LycaJoinModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class LycaJoinModelImpl implements LycaJoinModel {

    public static final String RESOURCE_TYPE = "lyca-spa-react/components/joinLyca";

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ChildResource(name = "ctaItems")
    private List<LycaCtaItemModel> ctaItems;

    @ValueMapValue
    private String heading;

    @ChildResource(name = "ctas")
    private List<LycaCtaButton> ctaButtons;

    @ValueMapValue
    private String placeholder;

    @ValueMapValue
    private String submitText;

    @ValueMapValue
    private String submitLink;

    @ValueMapValue
    private String promotionText;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<LycaCtaItemModel> getCtaItems() {
        return ctaItems;
    }

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public List<LycaCtaButton> getCtas() {
        return ctaButtons;
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public String getSubmitText() {
        return submitText;
    }

    @Override
    public String getSubmitLink() {
        return submitLink;
    }

    @Override
    public String getPromotionText() {
        return promotionText;
    }

    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

}
