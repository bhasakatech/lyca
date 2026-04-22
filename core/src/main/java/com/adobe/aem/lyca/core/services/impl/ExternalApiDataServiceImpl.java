package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.osgiconfig.ExternalApiDataConfig;
import com.adobe.aem.lyca.core.services.ExternalApiDataService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Component(service = ExternalApiDataService.class, immediate = true)
@Designate(ocd = ExternalApiDataConfig.class)
public class ExternalApiDataServiceImpl implements ExternalApiDataService {

    private static final Logger LOG = LoggerFactory.getLogger(ExternalApiDataServiceImpl.class);

    private String apiBaseUrl;
    private boolean enabled;

    @Activate
    @Modified
    protected void activate(ExternalApiDataConfig config) {
        this.apiBaseUrl = config.apiUrl();
        this.enabled    = config.enabled();
        LOG.info("ApiDataService activated. URL={}, enabled={}", apiBaseUrl, enabled);
    }

    @Override
    public String getApiData() {

        if (!enabled) {
            LOG.info("Service disabled. Skipping API call.");
            return "{\"status\":\"disabled\",\"data\":[]}";
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(apiBaseUrl);
            httpGet.setHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

                int statusCode = response.getStatusLine().getStatusCode();

                String responseBody = response.getEntity() != null
                        ? EntityUtils.toString(response.getEntity())
                        : "";

                LOG.info("API Status Code: {}", statusCode);

                if (statusCode == 200) {
                    return "{\"status\":\"success\",\"data\":" + responseBody + "}";
                } else {
                    return "{\"status\":\"error\",\"data\":[]}";
                }
            }

        } catch (IOException e) {
            LOG.error("Error while calling external API", e);
            return "{\"status\":\"error\",\"data\":[]}";
        }
    }
}