package com.adobe.aem.lyca.core.servlets;

import com.adobe.aem.lyca.core.services.ApiContentTableService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/lyca/api-data")
public class ApiContentTableServlet extends SlingSafeMethodsServlet {

    @Reference
    private ApiContentTableService apiService;

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {

        JSONArray data = apiService.fetchData();

        response.setContentType("application/json");
        response.getWriter().write(data.toString());
    }
}
