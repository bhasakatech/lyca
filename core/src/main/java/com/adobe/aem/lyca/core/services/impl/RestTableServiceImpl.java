package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.osgiconfig.RestTableConfig;
import com.adobe.aem.lyca.core.services.RestTableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link RestTableService} responsible for:
 * <ul>
 *     <li>Fetching data from an external REST API</li>
 *     <li>Parsing JSON response into Java objects</li>
 *     <li>Controlling execution based on OSGi configuration</li>
 * </ul>
 *
 * <p>This service uses Apache HttpClient to make HTTP calls and Jackson ObjectMapper
 * to convert JSON response into a list of maps.</p>
 *
 * <p>Configuration values such as API URL and enable/disable flag
 * are injected via OSGi configuration ({@link RestTableConfig}).</p>
 *
 * <p>This component is registered as an OSGi service and is activated immediately.</p>
 *
 * @implNote This implementation expects the API response to be a JSON array.
 */
@Component(
        service = RestTableService.class,
        immediate = true
)
@Slf4j
@Designate(ocd = RestTableConfig.class)
public class RestTableServiceImpl implements RestTableService {

    /**
     * API endpoint URL configured via OSGi.
     */
    private String apiUrl;

    /**
     * Flag to enable/disable API execution.
     */
    private boolean enabled;

    /**
     * Initializes or updates the configuration values.
     *
     * <p>This method is called during component activation and whenever
     * the OSGi configuration is modified.</p>
     *
     * @param config OSGi configuration containing API details
     */
    @Activate
    @Modified
    public void init(RestTableConfig config) {
        apiUrl = config.api_url();
        enabled = config.enabled();
    }

    /**
     * Calls the external REST API and returns the response data.
     *
     * <p>If the service is disabled via configuration, an empty list is returned.</p>
     *
     * <p>Flow:
     * <ol>
     *     <li>Validate if service is enabled</li>
     *     <li>Execute HTTP GET request</li>
     *     <li>Read response body</li>
     *     <li>Convert JSON into List&lt;Map&lt;String, Object&gt;&gt;</li>
     * </ol>
     *
     * @return List of maps representing API response data
     * @throws RuntimeException if API call fails or response parsing fails
     */
    @Override
    public List<Map<String, Object>> executeApi() {

        if (!enabled) {
            log.info("Configuration is disabled. Please enable first!");
            return List.of();
        }

        log.info("Constructed URL: {}", apiUrl);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(apiUrl);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {

                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                log.info("HTTP Status Code: {}", statusCode);

                ObjectMapper mapper = new ObjectMapper();

                List<Map<String, Object>> data =
                        mapper.readValue(
                                responseBody,
                                new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Object>>>() {}
                        );

                log.info("Fetched Data: {}", data);

                return data;
            }

        } catch (IOException e) {
            log.error("Error while calling external API", e);
            throw new RuntimeException("Error while calling external API", e);
        }
    }

    /**
     * Returns the configured API URL.
     *
     * @return API URL
     */
    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Indicates whether the API service is enabled.
     *
     * @return true if enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}