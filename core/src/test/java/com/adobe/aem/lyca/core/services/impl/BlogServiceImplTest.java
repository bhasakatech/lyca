package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.beans.BlogData;
import com.adobe.aem.lyca.core.utils.NPUtilService;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for {@link BlogServiceImpl}.
 *
 * <p>
 * This class validates BlogServiceImpl which fetches blog data from AEM Content Fragments
 * and maps them into {@link BlogData} objects.
 * </p>
 *
 * <p>
 * It uses:
 * <ul>
 *     <li>{@link AemContext} for AEM repository simulation</li>
 *     <li>Mockito for mocking dependencies (NPUtilService, ContentFragment, etc.)</li>
 *     <li>JUnit 5 for test execution</li>
 * </ul>
 * </p>
 *
 * <p>
 * Covered scenarios:
 * <ul>
 *     <li>Successful blog retrieval</li>
 *     <li>Null parent path handling</li>
 *     <li>Empty parent path handling</li>
 *     <li>Null Content Fragment scenario</li>
 *     <li>Null Content Element scenario</li>
 * </ul>
 * </p>
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BlogServiceImplTest {

    /**
     * AEM mock context used to simulate JCR repository.
     */
    private final AemContext context = new AemContext();

    /**
     * Service under test.
     */
    private BlogServiceImpl service;

    /**
     * Mocked NPUtilService used to provide ResourceResolver.
     */
    private NPUtilService npUtilService;

    /**
     * Sets up test environment before each test case.
     */
    @BeforeEach
    void setUp() {

        service = new BlogServiceImpl();
        npUtilService = mock(NPUtilService.class);
        service.npUtilService = npUtilService;
        context.load().json("/blogsServImpl.json", "/content");
    }

    /**
     * Tests successful retrieval of blogs from Content Fragments.
     */
    @Test
    void testGetBlogs_Success() throws Exception {

        ResourceResolver resolver = context.resourceResolver();

        ResourceResolver spyResolver = spy(resolver);
        Resource resource = spy(resolver.getResource("/content/dam/blog1"));
        ContentFragment fragment = mock(ContentFragment.class);
        ContentElement element = mock(ContentElement.class);

        doReturn(fragment).when(resource).adaptTo(ContentFragment.class);
        when(fragment.getElement(anyString())).thenReturn(element);
        when(element.getContent()).thenReturn("test-value");

        Iterator<Resource> iterator = List.of(resource).iterator();
        doReturn(iterator).when(spyResolver)
                .findResources(anyString(), eq("JCR-SQL2"));

        when(npUtilService.getResourceResolver()).thenReturn(spyResolver);
        List<BlogData> result = service.getBlogs("/content/dam");

        assertEquals(1, result.size());
        assertEquals("test-value", result.get(0).getBlogTitle());
        assertEquals("test-value", result.get(0).getBlogDescription());
        assertEquals("/content/dam/blog1", result.get(0).getFragmentPath());
    }

    /**
     * Tests behavior when parent path is null.
     */
    @Test
    void testParentPathNull() {

        List<BlogData> result = service.getBlogs(null);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests behavior when parent path is empty.
     */
    @Test
    void testParentPathEmpty() {

        List<BlogData> result = service.getBlogs("");
        assertTrue(result.isEmpty());
    }

    /**
     * Tests scenario where Content Fragment is null.
     */
    @Test
    void testFragmentNull() throws Exception {

        ResourceResolver resolver = context.resourceResolver();
        ResourceResolver spyResolver = spy(resolver);

        Resource resource = spy(resolver.getResource("/content/dam/blog1"));

        doReturn(null).when(resource).adaptTo(ContentFragment.class);

        Iterator<Resource> iterator = List.of(resource).iterator();
        doReturn(iterator).when(spyResolver)
                .findResources(anyString(), eq("JCR-SQL2"));

        when(npUtilService.getResourceResolver()).thenReturn(spyResolver);

        List<BlogData> result = service.getBlogs("/content/dam");
        assertTrue(result.isEmpty());
    }

    /**
     * Tests scenario where Content Fragment element is null.
     */
    @Test
    void testElementNull() throws Exception {

        ResourceResolver resolver = context.resourceResolver();
        ResourceResolver spyResolver = spy(resolver);

        Resource resource = spy(resolver.getResource("/content/dam/blog1"));

        ContentFragment fragment = mock(ContentFragment.class);

        doReturn(fragment).when(resource).adaptTo(ContentFragment.class);
        when(fragment.getElement(anyString())).thenReturn(null);

        Iterator<Resource> iterator = List.of(resource).iterator();
        doReturn(iterator).when(spyResolver)
                .findResources(anyString(), eq("JCR-SQL2"));

        when(npUtilService.getResourceResolver()).thenReturn(spyResolver);

        List<BlogData> result = service.getBlogs("/content/dam");
        assertEquals("", result.get(0).getBlogTitle());
    }
}