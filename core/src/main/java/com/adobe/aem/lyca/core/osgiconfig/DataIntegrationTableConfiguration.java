package com.adobe.aem.lyca.core.osgiconfig;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition
public @interface DataIntegrationTableConfiguration {

    @AttributeDefinition
    String apiUrl() default "https://jsonplaceholder.typicode.com/users";
    @AttributeDefinition
    boolean enable() default true;
}
