package com.adobe.aem.lyca.core.utils;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.LoginException;
import java.util.HashMap;
import java.util.Map;
/**
 * Utility service for obtaining a service {@link ResourceResolver}.
 *
 * <p>
 * This class provides a method to get a service resource resolver
 * using a configured system user (subservice). It is commonly used
 * to access repository resources securely without relying on
 * request-based authentication.
 * </p>
 */
@Component(service = NPUtilService.class)
public class NPUtilService {
    /**
     * Subservice name configured for service user mapping.
     */
    private static final String SERVICE = "lyca-service";

    /**
     * Factory to create {@link ResourceResolver} instances.
     */
    @Reference
    private ResourceResolverFactory resolverFactory;

    /**
     * Returns a service resource resolver using the configured subservice.
     *
     * <p>
     * This method uses {@link ResourceResolverFactory#SUBSERVICE} to
     * authenticate as a system user and obtain access to repository resources.
     * </p>
     *
     * @return a service {@link ResourceResolver}
     * @throws LoginException if unable to login with the service user
     */
    public ResourceResolver getResourceResolver() throws LoginException {

        Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, SERVICE);

        try {

            return resolverFactory.getServiceResourceResolver(map);

        } catch (Exception e) {
            throw new RuntimeException("Unable to get service resource resolver", e);
        }
    }
}
