package com.adobe.aem.lyca.core.utils;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.LoginException;
import java.util.HashMap;
import java.util.Map;

@Component(service = NPUtilService.class)
public class NPUtilService {

    private static final String SERVICE = "lyca-cf-service";

    @Reference
    private ResourceResolverFactory resolverFactory;

    public ResourceResolver getResourceResolver() throws LoginException, org.apache.sling.api.resource.LoginException {

        Map<String, Object> map = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE, SERVICE);

        try {

            return resolverFactory.getServiceResourceResolver(map);

        } catch (Exception e) {
            throw new RuntimeException("Unable to get service resource resolver", e);
        }
    }
}
