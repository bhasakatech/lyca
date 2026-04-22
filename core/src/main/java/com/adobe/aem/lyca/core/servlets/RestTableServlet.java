package com.adobe.aem.lyca.core.servlets;

import com.adobe.aem.lyca.core.services.RestTableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Sling Servlet to expose REST API data as JSON response.
 *
 * <p>This servlet acts as a bridge between the frontend (AEM component)
 * and the backend service ({@link RestTableService}). It fetches data
 * from the service layer and returns it as a JSON response.</p>
 *
 * <p><b>Servlet Path:</b> /bin/lyca/rest-table</p>
 *
 * <p><b>Usage:</b>
 * <ul>
 *     <li>Frontend (React/AEM component) calls this endpoint</li>
 *     <li>Servlet invokes {@link RestTableService#executeApi()}</li>
 *     <li>Response is converted into JSON and returned</li>
 * </ul>
 *
 * <p>This servlet supports only HTTP GET requests.</p>
 */
@Component(service = Servlet.class)
@SlingServletPaths("/bin/lyca/rest-table")
public class RestTableServlet extends SlingSafeMethodsServlet {

    /**
     * Reference to the RestTableService to fetch API data.
     */
    @Reference
    private RestTableService tableService;

    /**
     * Handles HTTP GET requests.
     *
     * <p>Flow:
     * <ol>
     *     <li>Calls the service layer to fetch API data</li>
     *     <li>Converts data into JSON format</li>
     *     <li>Writes JSON response to output</li>
     * </ol>
     *
     * @param request  Sling HTTP request
     * @param response Sling HTTP response
     * @throws ServletException if servlet-specific error occurs
     * @throws IOException      if input/output error occurs
     */
    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(tableService.executeApi());

            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.getWriter().write(jsonResponse);

        } catch (Exception e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Failed to fetch data\"}");
        }
    }
}