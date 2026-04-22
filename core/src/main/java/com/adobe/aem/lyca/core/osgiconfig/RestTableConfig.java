package com.adobe.aem.lyca.core.osgiconfig;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Rest Table Api Configuration")
public @interface RestTableConfig {

    @AttributeDefinition(name = "API URL")
    public String api_url() default "";

    @AttributeDefinition(name = "Enabled")
    boolean enabled() default true;
}
