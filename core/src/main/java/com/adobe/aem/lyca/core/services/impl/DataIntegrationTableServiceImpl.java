package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.osgiconfig.DataIntegrationTableConfiguration;
import com.adobe.aem.lyca.core.services.DataIntegrationTableService;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component(service = DataIntegrationTableService.class, immediate = true)
@Designate(ocd = DataIntegrationTableConfiguration.class)
public class DataIntegrationTableServiceImpl implements DataIntegrationTableService {

    private String apiUrl;
    private boolean enable;

    private static final Logger LOG =
            LoggerFactory.getLogger(DataIntegrationTableServiceImpl.class);

    @Activate
    @Modified
    protected void activate(DataIntegrationTableConfiguration config) {
        this.apiUrl = config.apiUrl();
        this.enable = config.enable();

        LOG.info("Config Loaded - API: {}, Enabled: {}", apiUrl, enable);
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    @Override
    public String fetchApiData() {

        if (!enable) {
            LOG.warn("API is disabled via config");
            return "{\"status\":\"disabled\"}";
        }

        String result = "{}";

        try {
            LOG.info("Calling API: {}", apiUrl);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            LOG.info("Status Code: {}", response.statusCode());

            if (response.statusCode() == 200) {
                result = response.body();
            } else {
                LOG.warn("Non-success response: {}", response.statusCode());
            }

        } catch (Exception e) {
            LOG.error("Error while calling API", e);
        }

        return result;
    }
}