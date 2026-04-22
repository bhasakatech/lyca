package com.adobe.aem.lyca.core.osgiconfig;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Lyca API Configuration")
public @interface ApiContentTableConfig {

    @AttributeDefinition(name = "API URL")
    String apiUrl() default "";

    @AttributeDefinition(name = "Enable API")
    boolean enabled() default false;
}

