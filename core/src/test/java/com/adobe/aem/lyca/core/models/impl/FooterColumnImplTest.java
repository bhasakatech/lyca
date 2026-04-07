package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import com.adobe.aem.lyca.core.models.FooterColumn;
import com.adobe.aem.lyca.core.models.FooterLink;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class FooterColumnImplTest {
    private final AemContext context = new AemContext();
    private FooterColumn footerColumn;
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(
                FooterColumnImpl.class,
                FooterLinkImpl.class
        );
        context.load().json("/footer.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/footer/footerLinks/item0");
        footerColumn = resource.adaptTo(FooterColumn.class);
    }
    @Test
    void testModelNotNull() {
        assertNotNull(footerColumn);
    }
    @Test
    void testTitle() {
        assertEquals("Join Lyca Mobile", footerColumn.getTitle());
    }
    @Test
    void testLinks() {
        List<FooterLink> links = footerColumn.getLinks();
        assertNotNull(links);
        assertEquals(4, links.size());
        assertEquals("Home", links.get(0).getNavigationLabel());
        assertEquals("#", links.get(0).getNavigationURL());
        assertEquals("Plans", links.get(1).getNavigationLabel());
        assertEquals("#", links.get(1).getNavigationURL());
    }
}