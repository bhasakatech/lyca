package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class HeroSlideTest {

    private final AemContext aemContext = new AemContext();
    private HeroSlide heroSlide;

    @BeforeEach
    public void init() {
        aemContext.addModelsForClasses(HeroSlide.class);
        aemContext.load().json("/hero-slide.json", "/content/test");

        Resource resource = aemContext.resourceResolver().getResource("/content/test");
        Assertions.assertNotNull(resource);

        heroSlide = resource.adaptTo(HeroSlide.class);
        Assertions.assertNotNull(heroSlide);
    }

    @Test
    void testPreTitle() {
        Assertions.assertEquals("Unlimited", heroSlide.getPreTitle());
    }

    @Test
    void testTitle() {
        Assertions.assertEquals("High-Speed Data", heroSlide.getTitle());
    }

    @Test
    void testSubtitle() {
        Assertions.assertEquals("Your day, powered by speed", heroSlide.getSubtitle());
    }

    @Test
    void testPrice() {
        Assertions.assertEquals("$18", heroSlide.getPrice());
    }

    @Test
    void testDuration() {
        Assertions.assertEquals("/30 days", heroSlide.getDuration());
    }

    @Test
    void testCtaText() {
        Assertions.assertEquals("Get it now", heroSlide.getCtaText());
    }

    @Test
    void testCtaLink() {
        Assertions.assertEquals("/content/lyca/page", heroSlide.getCtaLink());
    }

    @Test
    void testBgImage() {
        Assertions.assertEquals("/content/dam/lyca/banner.png", heroSlide.getBgImage());
    }

    @Test
    void testBgColor() {
        Assertions.assertEquals("rgb(1,72,190)", heroSlide.getBgColor());
    }

    @Test
    void testAllProperties() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Unlimited", heroSlide.getPreTitle()),
                () -> Assertions.assertEquals("High-Speed Data", heroSlide.getTitle()),
                () -> Assertions.assertEquals("Your day, powered by speed", heroSlide.getSubtitle()),
                () -> Assertions.assertEquals("$18", heroSlide.getPrice()),
                () -> Assertions.assertEquals("/30 days", heroSlide.getDuration()),
                () -> Assertions.assertEquals("Get it now", heroSlide.getCtaText()),
                () -> Assertions.assertEquals("/content/lyca/page", heroSlide.getCtaLink()),
                () -> Assertions.assertEquals("/content/dam/lyca/banner.png", heroSlide.getBgImage()),
                 ()-> Assertions.assertEquals("rgb(1,72,190)", heroSlide.getBgColor())
        );
    }
}