package com.adobe.aem.lyca.core.models;

import com.adobe.cq.wcm.core.components.models.Navigation;
import com.adobe.cq.wcm.core.components.models.NavigationItem;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class NavigationModelImplTest {

    AemContext context = new AemContext();

    private SpaNavigationModelImpl model; 
    private Navigation navigation;

    @BeforeEach
    void setUp() throws Exception {

        context.addModelsForClasses(SpaNavigationModelImpl.class);

        context.load().json("/spa-navigation.json", "/content");

        context.currentResource("/content");

        model = context.request().adaptTo(SpaNavigationModelImpl.class);

        navigation = mock(Navigation.class);
    }

    @Test
    void testModelProperties() {
        assertNotNull(model);
        assertEquals("/content/dam/lyca-spa-react/logo.png", model.getNavLogo());
        assertEquals("Login", model.getButtonLabel());
        assertEquals("/content/lyca-spa-react/us/en/login", model.getButtonLink());
        assertEquals(SpaNavigationModelImpl.RESOURCE_TYPE, model.getExportedType());
    }

    @Test
    void testGetItems_NavigationNull() {
        assertNull(model.getItems());
    }

    @Test
    void testGetItems_ItemsNull() throws Exception {
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(null);

        injectNavigation(nav);

        assertNull(model.getItems());
    }

    @Test
    void testGetItems_EmptyList() throws Exception {
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(Collections.emptyList());

        injectNavigation(nav);

        List<NavigationItem> result = model.getItems();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void testGetItems_LessThanFour() throws Exception {
        List<NavigationItem> items = Arrays.asList(
                mock(NavigationItem.class),
                mock(NavigationItem.class)
        );

        Navigation nav = Mockito.mock(Navigation.class);
        Mockito.when(nav.getItems()).thenReturn(items);

        injectNavigation(nav);

        List<NavigationItem> result = model.getItems();

        assertEquals(2, result.size());
        assertEquals(items, result);
    }

    @Test
    void testGetItems_ExactlyFour() throws Exception {
        List<NavigationItem> items = Arrays.asList(
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class)
        );

        Navigation nav = Mockito.mock(Navigation.class);
        Mockito.when(nav.getItems()).thenReturn(items);

        injectNavigation(nav);

        List<NavigationItem> result = model.getItems();

        assertEquals(4, result.size());
        assertEquals(items, result);
    }

    @Test
    void testGetItems_MoreThanFour() throws Exception {
        List<NavigationItem> items = Arrays.asList(
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class),
                Mockito.mock(NavigationItem.class)
        );

        Navigation nav = Mockito.mock(Navigation.class);
        Mockito.when(nav.getItems()).thenReturn(items);

        injectNavigation(nav);

        List<NavigationItem> result = model.getItems();

        assertEquals(4, result.size());
    }

    // Helper method to inject Navigation
    private void injectNavigation(Navigation nav) throws Exception {
        Field field = SpaNavigationModelImpl.class.getDeclaredField("navigation");
        field.setAccessible(true);
        field.set(model, nav);
    }

}