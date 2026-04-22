package com.adobe.aem.lyca.core.osgiconfig;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "External API Data Configuration")
public @interface ExternalApiDataConfig {
    @AttributeDefinition(name = "API Url")
    String apiUrl() default "https://fakestoreapiserver.reactbd.org/api/products";

    @AttributeDefinition(name = "Enable API")
    boolean enabled() default true;
}
