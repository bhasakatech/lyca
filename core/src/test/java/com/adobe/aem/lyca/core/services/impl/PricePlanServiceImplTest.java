package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanCFModel;
import com.adobe.aem.lyca.core.utils.NPUtilService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.wrappers.ResourceResolverWrapper;
import org.apache.sling.api.resource.ResourceWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit test class for {@link PricePlanServiceImpl}.
 *
 * This class tests different scenarios of fetching price plans
 * using mocked AEM resources and ResourceResolver.
 *
 * It uses AEM Mocks (AemContext) to simulate repository behavior
 * without requiring a real AEM instance.
 */
@ExtendWith(AemContextExtension.class)
class PricePlanServiceImplTest {

    /** AEM mock context used to simulate repository and resources */
    private final AemContext context = new AemContext();

    /** Service under test */
    private PricePlanServiceImpl service;

    /**
     * Setup method executed before each test.
     *
     * Initializes:
     * - Sling Models
     * - Test content (mock data)
     * - Mock ResourceResolver
     * - Injects mocked NPUtilService into service
     */
    @BeforeEach
    void init() throws Exception {

        /** Register Sling Model for adaptation */
        context.addModelsForClasses(PricePlanCFModel.class);

        /** Create mock content representing a price plan */
        context.create().resource("/content/plan",
                "planTitle", "Starter",
                "priceMonthly", 5.0,
                "priceYearly", 50.0,
                "dataLimit", "5GB",
                "features", new String[]{"Feature1", "Feature2"},
                "isPopular", false,
                "ctaLabel", "Buy Now",
                "ctaLink", "/buy"
        );

        /** Initialize service */
        service = new PricePlanServiceImpl();

        /**
         * Create a mock ResourceResolver
         * Overrides findResources() to return test resource
         */
        ResourceResolver resolver = new ResourceResolverWrapper(context.resourceResolver()) {
            @Override
            public Iterator<Resource> findResources(String query, String language) {

                /** Fetch test resource */
                Resource resource = context.resourceResolver().getResource("/content/plan");

                /**
                 * Wrap resource to simulate AEM structure:
                 * jcr:content/data/master
                 */
                Resource wrapped = new ResourceWrapper(resource) {
                    @Override
                    public Resource getChild(String path) {
                        if ("jcr:content/data/master".equals(path)) {
                            return resource;
                        }
                        return null;
                    }
                };

                /** Return single resource as iterator */
                return Collections.singletonList(wrapped).iterator();
            }
        };

        /**
         * Mock NPUtilService to return custom resolver
         */
        NPUtilService mockService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return resolver;
            }
        };

        /**
         * Inject mock service into PricePlanServiceImpl
         * using reflection (since OSGi is not available in unit tests)
         */
        Field field = PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, mockService);
    }

    /**
     * Test case for successful retrieval of price plans.
     *
     * Verifies:
     * - Plans list is not null
     * - Correct number of plans
     * - Correct mapping of fields
     */
    @Test
    void testGetPricePlans() {

        List<PricePlan> plans = service.getPricePlans("/content");

        assertNotNull(plans);
        assertEquals(1, plans.size());

        PricePlan plan = plans.get(0);

        assertEquals("Starter", plan.getPlanTitle());
        assertEquals(5.0, plan.getPriceMonthly());
        assertEquals(50.0, plan.getPriceYearly());
        assertEquals("Buy Now", plan.getCtaLabel());
        assertEquals("/buy", plan.getCtaLink());
    }

    /**
     * Test case to verify behavior when exception occurs
     * while getting ResourceResolver.
     *
     * Verifies:
     * - Method handles exception gracefully
     * - Returns empty list
     */
    @Test
    void testGetPricePlans_Exception() throws Exception {

        NPUtilService mockService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                throw new RuntimeException("Unexpected error");
            }
        };
        Field field = PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, mockService);
        List<PricePlan> result = service.getPricePlans("/content");
        assertTrue(result.isEmpty());
    }

    /**
     * Test case when Sling Model adaptation fails (CFModel is null).
     *
     * Verifies:
     * - adaptTo() returns null
     * - Plan is skipped
     * - Empty list is returned
     */
    @Test
    void testGetPricePlans_CFModelNull() throws Exception {

        Resource resource = context.create().resource("/content/test");

        ResourceResolver resolver = new ResourceResolverWrapper(context.resourceResolver()) {
            @Override
            public Iterator<Resource> findResources(String query, String language) {

                Resource wrapped = new ResourceWrapper(resource) {
                    @Override
                    public Resource getChild(String path) {
                        /**
                         * Return resource where adaptation fails
                         */
                        return new ResourceWrapper(resource) {
                            @Override
                            public <T> T adaptTo(Class<T> type) {
                                return null;
                            }
                        };
                    }
                };
                return Collections.singletonList(wrapped).iterator();
            }
        };
        NPUtilService mockService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return resolver;
            }
        };
        Field field = PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, mockService);
        List<PricePlan> result = service.getPricePlans("/content");
        assertTrue(result.isEmpty());
    }

    /**
     * Test case when data resource (jcr:content/data/master) is missing.
     *
     * Verifies:
     * - getChild() returns null
     * - Code enters warning block
     * - Resource is skipped
     */
    @Test
    void testGetPricePlans_DataResourceNull() throws Exception {

        Resource resource = context.create().resource("/content/test");

        ResourceResolver resolver = new ResourceResolverWrapper(context.resourceResolver()) {
            @Override
            public Iterator<Resource> findResources(String query, String language) {

                Resource wrapped = new ResourceWrapper(resource) {
                    @Override
                    public Resource getChild(String path) {
                        // simulate missing node
                        return null;
                    }
                };

                return Collections.singletonList(wrapped).iterator();
            }
        };

        NPUtilService mockService = new NPUtilService() {
            @Override
            public ResourceResolver getResourceResolver() {
                return resolver;
            }
        };
        Field field = PricePlanServiceImpl.class.getDeclaredField("npUtilService");
        field.setAccessible(true);
        field.set(service, mockService);
        List<PricePlan> result = service.getPricePlans("/content");
        assertTrue(result.isEmpty());
    }
}