package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class FooterLinkImplTest {
    private final AemContext context = new AemContext();
    private FooterLink footerLink;
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(FooterLinkImpl.class);
        context.load().json("/footer.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/footer/footerLinks/item0/links/item0");
        footerLink = resource.adaptTo(FooterLink.class);
    }
    @Test
    void testModelNotNull() {
        assertNotNull(footerLink);
    }
    @Test
    void testGetNavigationLabel() {
        assertEquals("Home", footerLink.getNavigationLabel());
    }
    @Test
    void testGetNavigationURL() {
        assertEquals("#", footerLink.getNavigationURL());
    }
}