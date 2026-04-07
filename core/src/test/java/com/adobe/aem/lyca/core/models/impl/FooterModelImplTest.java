package com.adobe.aem.lyca.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import com.adobe.aem.lyca.core.models.FooterColumn;
import com.adobe.aem.lyca.core.models.FooterLink;
import com.adobe.aem.lyca.core.models.FooterModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class FooterModelImplTest {
    private final AemContext context = new AemContext();
    private FooterModel footerModel;
    @BeforeEach
    void setUp() {
        context.addModelsForClasses(
                FooterModelImpl.class,
                FooterColumnImpl.class,
                FooterLinkImpl.class
        );
        context.load().json("/footer.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/footer");
        context.currentResource(resource);
        SlingHttpServletRequest request = context.request();
        footerModel = request.adaptTo(FooterModel.class);
    }
    @Test
    void testModelNotNull() {
        assertNotNull(footerModel);
    }
    @Test
    void testBasicProperties() {
        assertEquals("/content/dam/lyca-spa-react/images/lyca_mobile_logo.png", footerModel.getLycaLogo());
        assertTrue(footerModel.getLycaDescription().contains("Our motto"));
        assertTrue(footerModel.getCopyright().contains("LYCAMOBILE"));
    }
    @Test
    void testFooterLinks() {
        List<FooterColumn> columns = footerModel.getFooterLinks();
        assertNotNull(columns);
        assertEquals(3, columns.size());
        FooterColumn column1 = columns.get(0);
        assertEquals("Join Lyca Mobile", column1.getTitle());
        List<FooterLink> links1 = column1.getLinks();
        assertEquals(4, links1.size());
        assertEquals("Home", links1.get(0).getNavigationLabel());
        FooterColumn column2 = columns.get(1);
        assertEquals("Quick Links", column2.getTitle());
        FooterColumn column3 = columns.get(2);
        assertEquals("Help & Support", column3.getTitle());
    }
    @Test
    void testExportedType() {
        assertEquals(
                FooterModelImpl.RESOURCE_PATH,
                footerModel.getExportedType()
        );
    }
}