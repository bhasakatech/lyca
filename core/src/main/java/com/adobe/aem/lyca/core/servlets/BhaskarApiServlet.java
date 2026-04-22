package com.adobe.aem.lyca.core.servlets;

import com.adobe.aem.lyca.core.services.BhaskarApiService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/api/flexible",
                "sling.servlet.methods=GET"
        }
)
public class BhaskarApiServlet extends SlingSafeMethodsServlet {

    @Reference
    private BhaskarApiService apiService;

    @Override
    protected void doGet(SlingHttpServletRequest req,
                         SlingHttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-cache");

        String response = apiService.callApi();

        resp.getWriter().write(response);
    }
}