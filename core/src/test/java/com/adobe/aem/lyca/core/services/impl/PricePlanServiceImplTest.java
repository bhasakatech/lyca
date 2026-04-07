package com.adobe.aem.lyca.core.services.impl;
import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanCFModel;
import com.adobe.aem.lyca.core.utils.NPUtilService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import javax.jcr.query.Query;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PricePlanServiceImplTest {

    @Mock
    private NPUtilService npUtilService;
    @Mock
    private ResourceResolver resolver;
    @Mock
    private Iterator<Resource> resourceIterator;
    @Mock
    private Resource assetResource;
    @Mock
    private Resource dataResource;
    @Mock
    private PricePlanCFModel cfModel;
    @InjectMocks
    private PricePlanServiceImpl service;
    @Test
    void testGetPricePlans_Success() throws Exception {

        when(npUtilService.getResourceResolver()).thenReturn(resolver);

        when(resolver.findResources(anyString(), eq(Query.JCR_SQL2)))
                .thenReturn(resourceIterator);

        when(resourceIterator.hasNext()).thenReturn(true, false);
        when(resourceIterator.next()).thenReturn(assetResource);

        when(assetResource.getChild("jcr:content/data/master"))
                .thenReturn(dataResource);

        when(dataResource.adaptTo(PricePlanCFModel.class))
                .thenReturn(cfModel);

        // Mock CF values
        when(cfModel.getPlanTitle()).thenReturn("Starter");
        when(cfModel.getPriceMonthly()).thenReturn(5.0);
        when(cfModel.getPriceYearly()).thenReturn(50.0);
        when(cfModel.getDataLimit()).thenReturn("5GB");
        when(cfModel.getFeatures()).thenReturn(new String[]{"Calls", "SMS"});
        when(cfModel.isPopular()).thenReturn(true);
        when(cfModel.getCtaLabel()).thenReturn("Choose Plan");
        when(cfModel.getCtaLink()).thenReturn("/plans/starter");

        List<PricePlan> result = service.getPricePlans("/content/dam");

        assertNotNull(result);
        assertEquals(1, result.size());

        PricePlan plan = result.get(0);

        assertEquals("Starter", plan.getPlanTitle());
        assertEquals(5.0, plan.getPriceMonthly());
        assertEquals(50.0, plan.getPriceYearly());
        assertEquals("5GB", plan.getDataLimit());
        assertArrayEquals(new String[]{"Calls", "SMS"}, plan.getFeatures());
        assertTrue(plan.isPopular());
        assertEquals("Choose Plan", plan.getCtaLabel());
        assertEquals("/plans/starter", plan.getCtaLink());
    }
    @Test
    void testGetPricePlans_NoResults() throws Exception {

        when(npUtilService.getResourceResolver()).thenReturn(resolver);

        when(resolver.findResources(anyString(), eq(Query.JCR_SQL2)))
                .thenReturn(Collections.emptyIterator());

        List<PricePlan> result = service.getPricePlans("/content/dam");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_DataResourceNull() throws Exception {

        when(npUtilService.getResourceResolver()).thenReturn(resolver);

        when(resolver.findResources(anyString(), eq(Query.JCR_SQL2)))
                .thenReturn(resourceIterator);

        when(resourceIterator.hasNext()).thenReturn(true, false);
        when(resourceIterator.next()).thenReturn(assetResource);

        when(assetResource.getChild("jcr:content/data/master"))
                .thenReturn(null);

        List<PricePlan> result = service.getPricePlans("/content/dam");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_CFModelNull() throws Exception {

        when(npUtilService.getResourceResolver()).thenReturn(resolver);

        when(resolver.findResources(anyString(), eq(Query.JCR_SQL2)))
                .thenReturn(resourceIterator);

        when(resourceIterator.hasNext()).thenReturn(true, false);
        when(resourceIterator.next()).thenReturn(assetResource);

        when(assetResource.getChild("jcr:content/data/master"))
                .thenReturn(dataResource);

        when(dataResource.adaptTo(PricePlanCFModel.class))
                .thenReturn(null);

        List<PricePlan> result = service.getPricePlans("/content/dam");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_Exception() throws Exception {

        when(npUtilService.getResourceResolver())
                .thenThrow(new RuntimeException("Resolver error"));

        List<PricePlan> result = service.getPricePlans("/content/dam");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}