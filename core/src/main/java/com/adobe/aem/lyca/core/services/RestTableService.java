package com.adobe.aem.lyca.core.services;

import java.util.List;
import java.util.Map;

/**
 * Service interface for fetching and managing REST API data
 * to be consumed by AEM components (e.g., table rendering).
 *
 * <p>This service is responsible for:
 * <ul>
 *     <li>Calling an external REST API</li>
 *     <li>Returning response data in structured format</li>
 *     <li>Providing API configuration details</li>
 * </ul>
 */
public interface RestTableService {

    /**
     * Executes the configured REST API call and returns the response data.
     *
     * <p>The response is expected to be transformed into a list of key-value pairs,
     * where each map represents a single row of data.
     *
     * @return List of maps containing API response data
     *         (each map represents a record/row)
     */
    List<Map<String, Object>> executeApi();

    /**
     * Returns the configured API URL.
     *
     * <p>This value is usually injected via OSGi configuration
     * and may vary based on environment (Author/Publish).
     *
     * @return API endpoint URL as a String
     */
    String getApiUrl();

    /**
     * Indicates whether the API integration is enabled.
     *
     * <p>This flag allows turning ON/OFF API execution without code changes,
     * typically controlled via OSGi configuration.
     *
     * @return true if API is enabled, false otherwise
     */
    boolean isEnabled();
}