package com.adobe.aem.lyca.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(AemContextExtension.class)
class AppStoreTest {

    private final AemContext context = new AemContext();

    private AppStore appStore;

    @BeforeEach
    void setUp() {

        context.load().json("/AppStoreTest.json", "/content");
        Resource resource = context.resourceResolver().getResource("/content/appstore");

        appStore = resource.adaptTo(AppStore.class);
    }
    @Test
    void testWithData() {

        assertNotNull(appStore);

        assertEquals("/content/dam/icon.png", appStore.getAppStoreIcon());
        assertEquals("/content/link", appStore.getAppStoreLink());
    }
    @Test
    void testWithEmptyData() {

        Resource resource = context.resourceResolver().getResource("/content/empty");

        AppStore emptyModel = resource.adaptTo(AppStore.class);

        assertNotNull(emptyModel);

        assertNull(emptyModel.getAppStoreIcon());
        assertNull(emptyModel.getAppStoreLink());
    }
}