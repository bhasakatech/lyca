package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.beans.BlogData;
import com.adobe.aem.lyca.core.utils.NPUtilService;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BlogServiceImplTest {

    private final AemContext context = new AemContext();

    private BlogServiceImpl service;
    private NPUtilService npUtilService;

    @BeforeEach
    void setUp() {

        service = new BlogServiceImpl();
        npUtilService = mock(NPUtilService.class);
        service.npUtilService = npUtilService;
        context.load().json("/blogsServImpl.json", "/content");
    }
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
    @Test
    void testParentPathNull() {

        List<BlogData> result = service.getBlogs(null);
        assertTrue(result.isEmpty());
    }
    @Test
    void testParentPathEmpty() {

        List<BlogData> result = service.getBlogs("");
        assertTrue(result.isEmpty());
    }
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

        assertEquals("", result.get(0).getBlogTitle()); // covers getElement()
    }
    @Test
    void testLoginException() throws Exception {

        when(npUtilService.getResourceResolver()).thenThrow(LoginException.class);

        List<BlogData> result = service.getBlogs("/content/dam");

        assertTrue(result.isEmpty());
    }
    @Test
    void testJcrLoginException() throws Exception {

        when(npUtilService.getResourceResolver())
                .thenThrow(new javax.jcr.LoginException("error"));
        assertThrows(RuntimeException.class,
                () -> service.getBlogs("/content/dam"));
    }
}