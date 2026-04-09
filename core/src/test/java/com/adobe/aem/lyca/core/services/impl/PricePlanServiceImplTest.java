package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanCFModel;
import com.adobe.aem.lyca.core.utils.NPUtilService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.*;
import org.apache.sling.api.wrappers.ResourceResolverWrapper;
import org.apache.sling.api.resource.ResourceWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class PricePlanServiceImplTest {

    private final AemContext context = new AemContext();
    private PricePlanServiceImpl service;
    @BeforeEach
    void init() throws Exception {
        context.addModelsForClasses(PricePlanCFModel.class);
        context.load().json("/priceplancf.json", "/content");
        service = new PricePlanServiceImpl();
        NPUtilService utilService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return new ResourceResolverWrapper(context.resourceResolver()) {
                    @Override
                    public Iterator<Resource> findResources(String query, String language) {
                        List<Resource> list = new ArrayList<>();
                        Resource original = context.resourceResolver().getResource("/content/priceplan-valid");
                        if (original != null) {
                            Resource wrapped = new ResourceWrapper(original) {
                                @Override
                                public Resource getChild(String relPath) {
                                    if ("jcr:content/data/master".equals(relPath)) {
                                        return original;
                                    }
                                    return null;
                                }
                            };
                            list.add(wrapped);
                        }

                        return list.iterator();
                    }
                };
            }
        };
        java.lang.reflect.Field field =
                PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, utilService);
    }
    @Test
    void testGetPricePlans_Success() {
        List<PricePlan> result = service.getPricePlans("/content");
        assertNotNull(result);
        assertEquals(1, result.size());
        PricePlan plan = result.get(0);
        assertEquals("Starter", plan.getPlanTitle());
        assertEquals(5.0, plan.getPriceMonthly());
        assertEquals(50.0, plan.getPriceYearly());
        assertEquals("5GB Monthly Plan", plan.getDataLimit());
        assertArrayEquals(new String[]{
                "1000 UK minutes & texts",
                "100 international minutes",
                "5G ready"
        }, plan.getFeatures());

        assertFalse(plan.isPopular());
        assertEquals("Choose Plan", plan.getCtaLabel());
        assertEquals("/plans/starter", plan.getCtaLink());
    }
    @Test
    void testGetPricePlans_NoResults() throws Exception {
        NPUtilService utilService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return new ResourceResolverWrapper(context.resourceResolver()) {
                    @Override
                    public Iterator<Resource> findResources(String query, String language) {
                        if (query.contains("/invalid")) {
                            return Collections.emptyIterator();
                        }
                        return Collections.emptyIterator();
                    }
                };
            }
        };
        java.lang.reflect.Field field =
                PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, utilService);
        List<PricePlan> result = service.getPricePlans("/invalid");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_DataResourceNull() throws Exception {
        NPUtilService utilService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return new ResourceResolverWrapper(context.resourceResolver()) {
                    @Override
                    public Iterator<Resource> findResources(String query, String language) {
                        Resource res = context.create().resource("/content/test");
                        return Collections.singletonList(res).iterator();
                    }
                };
            }
        };

        inject(utilService);

        List<PricePlan> result = service.getPricePlans("/content");

        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_CFModelNull() throws Exception {

        NPUtilService utilService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return new ResourceResolverWrapper(context.resourceResolver()) {
                    @Override
                    public Iterator<Resource> findResources(String query, String language) {
                        Resource asset = context.create().resource("/content/test");
                        Resource data = new ResourceWrapper(asset) {
                            @Override
                            public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) {
                                return null;
                            }
                        };
                        Resource wrappedAsset = new ResourceWrapper(asset) {
                            @Override
                            public Resource getChild(String relPath) {
                                if ("jcr:content/data/master".equals(relPath)) {
                                    return data;
                                }
                                return null;
                            }
                        };

                        return Collections.singletonList(wrappedAsset).iterator();
                    }
                };
            }
        };
        inject(utilService);
        List<PricePlan> result = service.getPricePlans("/content");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_RepositoryException() throws Exception {

        NPUtilService utilService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {

                return new ResourceResolverWrapper(context.resourceResolver()) {

                    @Override
                    public Iterator<Resource> findResources(String query, String language) {

                        Resource asset = context.create().resource("/content/test");

                        Resource wrapped = new ResourceWrapper(asset) {
                            @Override
                            public Resource getChild(String relPath) {
                                throw new RuntimeException(new javax.jcr.RepositoryException("Repo error"));
                            }
                        };
                        return Collections.singletonList(wrapped).iterator();
                    }
                };
            }
        };
        inject(utilService);
        List<PricePlan> result = service.getPricePlans("/content");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void testGetPricePlans_Exception() throws Exception {

        NPUtilService utilService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                throw new RuntimeException("Unexpected error");
            }
        };
        inject(utilService);
        List<PricePlan> result = service.getPricePlans("/content");
        assertTrue(result.isEmpty());
    }
    private void inject(NPUtilService utilService) throws Exception {
        java.lang.reflect.Field field =
                PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, utilService);
    }

}