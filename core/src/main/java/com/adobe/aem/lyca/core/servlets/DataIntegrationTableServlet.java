package com.adobe.aem.lyca.core.servlets;

import com.adobe.aem.lyca.core.services.DataIntegrationTableService;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/api-table-data")
public class DataIntegrationTableServlet extends SlingSafeMethodsServlet {

    @Reference
    private DataIntegrationTableService service;

    @Override
    protected void doGet(org.apache.sling.api.SlingHttpServletRequest request,
                         org.apache.sling.api.SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        if (!service.isEnable()) {
            response.getWriter().write("{\"status\":\"disabled\"}");
            return;
        }

        String data = service.fetchApiData();
        response.getWriter().write(data);
    }
}