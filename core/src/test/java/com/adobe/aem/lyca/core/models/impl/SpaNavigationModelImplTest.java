package com.adobe.aem.lyca.core.models.impl;

import com.adobe.cq.wcm.core.components.models.Navigation;
import com.adobe.cq.wcm.core.components.models.NavigationItem;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test class for {@link SpaNavigationModelImpl}.
 *
 * <p>This test validates:
 * <ul>
 *     <li>Basic component property mappings</li>
 *     <li>Navigation item handling logic</li>
 *     <li>Edge cases like null and empty navigation</li>
 *     <li>Item limiting logic (max 4 items)</li>
 * </ul>
 *
 * <p>Technologies used:
 * <ul>
 *     <li>AEM Mocks (AemContext)</li>
 *     <li>JUnit 5</li>
 *     <li>Mockito (for Navigation mocking)</li>
 * </ul>
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SpaNavigationModelImplTest {

    /**
     * AEM mock context to simulate repository and Sling environment
     */
    AemContext context = new AemContext();

    /**
     *  Model under test
     */
    private SpaNavigationModelImpl model;

    /**
     * Navigation core component dependency
     */
    private Navigation navigation;

    /**
     * Initializes test setup before each test:
     * <ul>
     *     <li>Registers Sling Model</li>
     *     <li>Loads mock JSON content</li>
     *     <li>Sets current resource</li>
     *     <li>Adapts request to model</li>
     * </ul>
     */
    @BeforeEach
    void setUp() throws Exception {
        context.addModelsForClasses(SpaNavigationModelImpl.class);
        context.load().json("/spa-navigation.json", "/content");
        context.currentResource("/content");
        model = context.request().adaptTo(SpaNavigationModelImpl.class);
        navigation = context.request().adaptTo(Navigation.class);
    }

    /**
     * Validates basic component properties mapping from JCR.
     */
    @Test
    void testModelProperties() {
        assertNotNull(model);
        assertEquals("/content/dam/lyca-spa-react/logo.png", model.getNavLogo());
        assertEquals("Login", model.getButtonLabel());
        assertEquals("/content/lyca-spa-react/us/en/login", model.getButtonLink());
        assertEquals(SpaNavigationModelImpl.RESOURCE_TYPE, model.getExportedType());
    }

    /**
     * Scenario: Navigation object is not injected.
     * Expected: getItems() should return null.
     */
    @Test
    void testGetItems_NavigationNull() {
        assertNull(model.getItems());
    }

    /**
     * Scenario: Navigation exists but returns null items.
     * Expected: getItems() should return null.
     */
    @Test
    void testGetItems_ItemsNull() throws Exception {
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(null);
        injectNavigation(nav);
        assertNull(model.getItems());
    }

    /**
     * Scenario: Navigation returns empty list.
     * Expected: Empty list should be returned (not null).
     */
    @Test
    void testGetItems_EmptyList() throws Exception {
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(Collections.emptyList());
        injectNavigation(nav);
        List<NavigationItem> result = model.getItems();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    /**
     * Scenario: Navigation contains fewer than 4 items.
     * Expected: All items should be returned.
     */
    @Test
    void testGetItems_LessThanFour() throws Exception {
        List<NavigationItem> items = Arrays.asList(
                mock(NavigationItem.class),
                mock(NavigationItem.class)
        );
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(items);
        injectNavigation(nav);
        List<NavigationItem> result = model.getItems();
        assertEquals(2, result.size());
        assertEquals(items, result);
    }

    /**
     * Scenario: Navigation contains exactly 4 items.
     * Expected: All 4 items should be returned.
     */
    @Test
    void testGetItems_ExactlyFour() throws Exception {
        List<NavigationItem> items = Arrays.asList(
                mock(NavigationItem.class),
                mock(NavigationItem.class),
                mock(NavigationItem.class),
                mock(NavigationItem.class)
        );
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(items);
        injectNavigation(nav);
        List<NavigationItem> result = model.getItems();
        assertEquals(4, result.size());
        assertEquals(items, result);
    }

    /**
     * Scenario: Navigation contains more than 4 items.
     * Expected: Only first 4 items should be returned.
     */
    @Test
    void testGetItems_MoreThanFour() throws Exception {
        List<NavigationItem> items = Arrays.asList(
                mock(NavigationItem.class),
                mock(NavigationItem.class),
                mock(NavigationItem.class),
                mock(NavigationItem.class),
                mock(NavigationItem.class)
        );
        Navigation nav = mock(Navigation.class);
        when(nav.getItems()).thenReturn(items);
        injectNavigation(nav);
        List<NavigationItem> result = model.getItems();
        assertEquals(4, result.size());
    }

    /**
     * Helper method to inject mocked Navigation into the model using reflection.
     *
     * <p>This is required because:
     * <ul>
     *     <li>Navigation is internally injected (likely via @Self or @Inject)</li>
     *     <li>We want to override it with a mock for testing</li>
     * </ul>
     *
     * @param nav mocked Navigation object
     */
    private void injectNavigation(Navigation nav) throws Exception {
        Field field = SpaNavigationModelImpl.class.getDeclaredField("navigation");
        field.setAccessible(true);
        field.set(model, nav);
    }
}