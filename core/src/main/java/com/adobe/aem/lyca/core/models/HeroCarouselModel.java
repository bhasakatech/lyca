package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface HeroCarouselModel extends ComponentExporter {
    List<HeroSlide> getSlides();
}
