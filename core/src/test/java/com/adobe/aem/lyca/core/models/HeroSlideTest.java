package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Unit test class for {@link HeroSlide}.
 *
 * <p>This test class verifies the correct mapping of properties
 * from the Sling resource to the HeroSlide model.</p>
 *
 * <p>The test cases cover:</p>
 * <ul>
 *   <li>Successful adaptation of resource to {@link HeroSlide}</li>
 *   <li>Validation of individual properties such as preTitle, title,
 *       subtitle, price, duration, CTA text, CTA link, background image, and background color</li>
 *   <li>Verification of all properties together for completeness</li>
 * </ul>
 *
 * <p>AEM Mocks are used to simulate repository content and Sling Model behavior.</p>
 */
@ExtendWith(AemContextExtension.class)
class HeroSlideTest {

    /**
     * AEM mock context used to simulate repository resources.
     */
    private final AemContext aemContext = new AemContext();

    /**
     * Model instance under test.
     */
    private HeroSlide heroSlide;

    /**
     * Sets up the test context before each test execution.
     *
     * <p>This method loads mock JSON content, resolves the test resource,
     * and adapts it to the {@link HeroSlide} model.</p>
     */
    @BeforeEach
    public void init() {
        aemContext.addModelsForClasses(HeroSlide.class);
        aemContext.load().json("/hero-slide.json", "/content/test");

        Resource resource = aemContext.resourceResolver().getResource("/content/test");
        Assertions.assertNotNull(resource);

        heroSlide = resource.adaptTo(HeroSlide.class);
        Assertions.assertNotNull(heroSlide);
    }

    /**
     * Verifies that the preTitle property is correctly mapped.
     */
    @Test
    void testPreTitle() {
        Assertions.assertEquals("Unlimited", heroSlide.getPreTitle());
    }

    /**
     * Verifies that the title property is correctly mapped.
     */
    @Test
    void testTitle() {
        Assertions.assertEquals("High-Speed Data", heroSlide.getTitle());
    }

    /**
     * Verifies that the subtitle property is correctly mapped.
     */
    @Test
    void testSubtitle() {
        Assertions.assertEquals("Your day, powered by speed", heroSlide.getSubtitle());
    }

    /**
     * Verifies that the price property is correctly mapped.
     */
    @Test
    void testPrice() {
        Assertions.assertEquals("$18", heroSlide.getPrice());
    }

    /**
     * Verifies that the duration property is correctly mapped.
     */
    @Test
    void testDuration() {
        Assertions.assertEquals("/30 days", heroSlide.getDuration());
    }

    /**
     * Verifies that the CTA text is correctly mapped.
     */
    @Test
    void testCtaText() {
        Assertions.assertEquals("Get it now", heroSlide.getCtaText());
    }

    /**
     * Verifies that the CTA link is correctly mapped.
     */
    @Test
    void testCtaLink() {
        Assertions.assertEquals("/content/lyca/page", heroSlide.getCtaLink());
    }

    /**
     * Verifies that the background image path is correctly mapped.
     */
    @Test
    void testBgImage() {
        Assertions.assertEquals("/content/dam/lyca/banner.png", heroSlide.getBgImage());
    }

    /**
     * Verifies that the background color is correctly mapped.
     */
    @Test
    void testBgColor() {
        Assertions.assertEquals("rgb(1,72,190)", heroSlide.getBgColor());
    }

    /**
     * Verifies that all properties of the HeroSlide are correctly populated.
     *
     * <p>This test ensures complete validation of all mapped fields
     * in a single grouped assertion.</p>
     */
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
                () -> Assertions.assertEquals("rgb(1,72,190)", heroSlide.getBgColor())
        );
    }
}