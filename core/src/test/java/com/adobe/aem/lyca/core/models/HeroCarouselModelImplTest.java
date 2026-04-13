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

/**
 * Unit test class for {@link HeroCarouselModelImpl}.
 *
 * <p>This test class verifies the behavior of the Hero Carousel Sling Model,
 * including successful model adaptation, slide list initialization,
 * slide property mapping, and exported resource type validation.</p>
 *
 * <p>The test cases cover:</p>
 * <ul>
 *   <li>Successful adaptation of the resource to {@link HeroCarouselModel}</li>
 *   <li>Validation that the slides list is not null</li>
 *   <li>Verification of the total number of configured slides</li>
 *   <li>Validation of individual slide properties such as pre-title, title,
 *       subtitle, price, duration, CTA text, CTA link, and background image</li>
 *   <li>Verification of the exported resource type</li>
 *   <li>Validation of all slide data together for completeness</li>
 * </ul>
 *
 * <p>This class uses AEM Mocks to simulate repository content and Sling Model adaptation.</p>
 */
@ExtendWith(AemContextExtension.class)
class HeroCarouselModelImplTest {

    /**
     * AEM mock context used to simulate repository resources and Sling Model behavior.
     */
    private final AemContext context = new AemContext();

    /**
     * Model instance under test.
     */
    private HeroCarouselModel model;

    /**
     * Sets up the mock AEM context before each test.
     *
     * <p>This method registers the Sling Model classes, loads test content
     * from the mock JSON file, resolves the test resource, and adapts it
     * to the {@link HeroCarouselModel}.</p>
     */
    @BeforeEach
    public void init() {
        context.addModelsForClasses(HeroCarouselModelImpl.class, HeroSlide.class);
        context.load().json("/hero-carousel.json", "/content/test");

        Resource resource = context.resourceResolver().getResource("/content/test");
        assertNotNull(resource);

        model = resource.adaptTo(HeroCarouselModel.class);
        assertNotNull(model);
    }

    /**
     * Verifies that the resource is successfully adapted to the model.
     */
    @Test
    void testModelAdaptation() {
        assertNotNull(model);
    }

    /**
     * Verifies that the slides list is initialized and not null.
     */
    @Test
    void testGetSlidesNotNull() {
        assertNotNull(model.getSlides());
    }

    /**
     * Verifies that the expected number of slides is loaded from the mock content.
     */
    @Test
    void testSlidesSize() {
        List<HeroSlide> slides = model.getSlides();
        assertEquals(2, slides.size());
    }

    /**
     * Verifies that the first slide properties are correctly mapped from the mock content.
     */
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

    /**
     * Verifies that the second slide properties are correctly mapped from the mock content.
     */
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

    /**
     * Verifies that the exported resource type matches the expected component resource type.
     */
    @Test
    void testGetExportedType() {
        assertEquals(
                "lyca-spa-react/components/content/hero-carousel",
                model.getExportedType()
        );
    }

    /**
     * Verifies that all slide data is loaded and mapped correctly.
     *
     * <p>This test validates the slide count and all configured properties
     * of both slide entries to ensure completeness of the model data.</p>
     */
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