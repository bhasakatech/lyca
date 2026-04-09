package com.adobe.aem.lyca.core.utils;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class NPUtilServiceTest {

    private final AemContext context = new AemContext();
    @Mock
    private ResourceResolverFactory resolverFactory;
    @Mock
    private ResourceResolver resourceResolver;
    @InjectMocks
    private NPUtilService npUtilService;

    @BeforeEach
    void init() {
        context.addModelsForClasses(NPUtilService.class);
    }
    @Test
    void testGetResourceResolverSuccess() throws Exception {
        when(resolverFactory.getServiceResourceResolver(anyMap()))
                .thenReturn(resourceResolver);
        ResourceResolver result = npUtilService.getResourceResolver();
        assertNotNull(result);
        assertEquals(resourceResolver, result);
        verify(resolverFactory).getServiceResourceResolver(argThat(map ->
                map.containsKey(ResourceResolverFactory.SUBSERVICE) &&
                        "lyca-cf-service".equals(map.get(ResourceResolverFactory.SUBSERVICE))
        ));
    }
    @Test
    void testGetResourceResolverThrowsException() throws Exception {
        when(resolverFactory.getServiceResourceResolver(anyMap()))
                .thenThrow(new LoginException("Mock Login Exception"));
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                npUtilService.getResourceResolver()
        );
        assertEquals("Unable to get service resource resolver", exception.getMessage());
        assertNotNull(exception.getCause());
        assertTrue(exception.getCause() instanceof LoginException);
    }

    @Test
    void testServiceInvocation() throws Exception {
        when(resolverFactory.getServiceResourceResolver(anyMap()))
                .thenReturn(resourceResolver);
        npUtilService.getResourceResolver();
        verify(resolverFactory, times(1))
                .getServiceResourceResolver(anyMap());
    }
}