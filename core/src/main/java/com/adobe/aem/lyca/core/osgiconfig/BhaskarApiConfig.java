package com.adobe.aem.lyca.core.osgiconfig;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Bhaskar Flexible API Configuration")
public @interface BhaskarApiConfig {

    @AttributeDefinition(name = "API URL")
    String apiUrl() default "";

    @AttributeDefinition(name = "HTTP Method")
    String method() default "GET";

    @AttributeDefinition(name = "Enable API")
    boolean enableApi() default true;

    @AttributeDefinition(name = "Request Headers (key:value;key:value)")
    String headers() default "";

    @AttributeDefinition(name = "Timeout (ms)")
    int timeout() default 5000;
}