package com.adobe.aem.lyca.core.models;

import com.adobe.aem.lyca.core.models.impl.HeroCarouselModelImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class HeroCarouselModelImplTest {

    private final AemContext context = new AemContext();
    private HeroCarouselModel model;

    @BeforeEach
    public void init() {
        context.addModelsForClasses(HeroCarouselModelImpl.class, HeroSlide.class);
        context.load().json("/hero-carousel.json", "/content/test");

        Resource resource = context.resourceResolver().getResource("/content/test");
        assertNotNull(resource);

        model = resource.adaptTo(HeroCarouselModel.class);
        assertNotNull(model);
    }

    @Test
    void testModelAdaptation() {
        assertNotNull(model);
    }

    @Test
    void testGetSlidesNotNull() {
        assertNotNull(model.getSlides());
    }

    @Test
    void testSlidesSize() {
        List<HeroSlide> slides = model.getSlides();
        assertEquals(2, slides.size());
    }

    @Test
    void testFirstSlideProperties() {
        List<HeroSlide> slides = model.getSlides();
        HeroSlide firstSlide = slides.get(0);

        assertAll(
                () -> assertEquals("Unlimited", firstSlide.getPreTitle()),
                () -> assertEquals("High-Speed Data", firstSlide.getTitle()),
                () -> assertEquals("Your day, powered by speed", firstSlide.getSubtitle()),
                () -> assertEquals("$18", firstSlide.getPrice()),
                () -> assertEquals("/30 days", firstSlide.getDuration()),
                () -> assertEquals("Get it now", firstSlide.getCtaText()),
                () -> assertEquals("/content/lyca/page1", firstSlide.getCtaLink()),
                () -> assertEquals("/content/dam/lyca/banner1.png", firstSlide.getBgImage())
        );
    }

    @Test
    void testSecondSlideProperties() {
        List<HeroSlide> slides = model.getSlides();
        HeroSlide secondSlide = slides.get(1);

        assertAll(
                () -> assertEquals("New Offer", secondSlide.getPreTitle()),
                () -> assertEquals("Unlimited Calls", secondSlide.getTitle()),
                () -> assertEquals("Talk without limits", secondSlide.getSubtitle()),
                () -> assertEquals("$25", secondSlide.getPrice()),
                () -> assertEquals("/30 days", secondSlide.getDuration()),
                () -> assertEquals("Buy Now", secondSlide.getCtaText()),
                () -> assertEquals("/content/lyca/page2", secondSlide.getCtaLink()),
                () -> assertEquals("/content/dam/lyca/banner2.png", secondSlide.getBgImage())
        );
    }

    @Test
    void testGetExportedType() {
        assertEquals(
                "lyca-spa-react/components/content/hero-carousel",
                model.getExportedType()
        );
    }

    @Test
    void testAllSlidesData() {
        List<HeroSlide> slides = model.getSlides();

        assertAll(
                () -> assertEquals(2, slides.size()),

                () -> assertEquals("Unlimited", slides.get(0).getPreTitle()),
                () -> assertEquals("High-Speed Data", slides.get(0).getTitle()),
                () -> assertEquals("Your day, powered by speed", slides.get(0).getSubtitle()),
                () -> assertEquals("$18", slides.get(0).getPrice()),
                () -> assertEquals("/30 days", slides.get(0).getDuration()),
                () -> assertEquals("Get it now", slides.get(0).getCtaText()),
                () -> assertEquals("/content/lyca/page1", slides.get(0).getCtaLink()),
                () -> assertEquals("/content/dam/lyca/banner1.png", slides.get(0).getBgImage()),

                () -> assertEquals("New Offer", slides.get(1).getPreTitle()),
                () -> assertEquals("Unlimited Calls", slides.get(1).getTitle()),
                () -> assertEquals("Talk without limits", slides.get(1).getSubtitle()),
                () -> assertEquals("$25", slides.get(1).getPrice()),
                () -> assertEquals("/30 days", slides.get(1).getDuration()),
                () -> assertEquals("Buy Now", slides.get(1).getCtaText()),
                () -> assertEquals("/content/lyca/page2", slides.get(1).getCtaLink()),
                () -> assertEquals("/content/dam/lyca/banner2.png", slides.get(1).getBgImage())
        );
    }
}