package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.util.List;


@Model(
        adaptables = {SlingHttpServletRequest.class, Resource.class},
        adapters = {HeroCarouselModel.class, ComponentExporter.class},
        resourceType = HeroCarouselModelImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
      name =  ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class HeroCarouselModelImpl implements HeroCarouselModel{

    public static final String RESOURCE_TYPE="lyca-spa-react/components/content/hero-carousel";

    @ChildResource(name = "slides")
    private List<HeroSlide> slides;


    @Override
    public List<HeroSlide> getSlides() {
        return this.slides;
    }

    @Override
    public String getExportedType() {
        return HeroCarouselModelImpl.RESOURCE_TYPE;
    }
}
