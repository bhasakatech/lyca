package com.adobe.aem.lyca.core.servlets;

import com.adobe.aem.lyca.core.services.ExternalApiDataService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, immediate = true)
@SlingServletPaths({"/bin/external/api/data"})
public class ExternalApiDataServlet extends SlingSafeMethodsServlet {

    @Reference
    ExternalApiDataService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String apiData = service.getApiData();
        response.getWriter().write(apiData);
    }
}
