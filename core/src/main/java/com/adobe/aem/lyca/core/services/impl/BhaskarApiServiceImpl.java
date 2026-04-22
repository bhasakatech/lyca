package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.osgiconfig.BhaskarApiConfig;
import com.adobe.aem.lyca.core.services.BhaskarApiService;

import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component(service = BhaskarApiService.class)
@Designate(ocd = BhaskarApiConfig.class)
public class BhaskarApiServiceImpl implements BhaskarApiService {

    private static final Logger LOG = LoggerFactory.getLogger(BhaskarApiServiceImpl.class);

    private BhaskarApiConfig config;

    @Activate
    @Modified
    protected void activate(BhaskarApiConfig config) {
        this.config = config;
    }

    @Override
    public String callApi() {

        try {

            if (config == null || !config.enableApi()) {
                LOG.warn("API disabled via config");
                return "{\"error\":\"API disabled via config\"}";
            }

            if (config.apiUrl() == null || config.apiUrl().isEmpty()) {
                return "{\"error\":\"API URL not configured\"}";
            }

            URL url = new URL(config.apiUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(config.method().toUpperCase());
            conn.setConnectTimeout(config.timeout());
            conn.setReadTimeout(config.timeout());

            if (config.headers() != null && !config.headers().isEmpty()) {
                String[] headerPairs = config.headers().split(";");
                for (String pair : headerPairs) {
                    if (pair.contains(":")) {
                        String[] kv = pair.split(":", 2);
                        conn.setRequestProperty(kv[0].trim(), kv[1].trim());
                    }
                }
            }

            int responseCode = conn.getResponseCode();
            LOG.info("API Response Code: {}", responseCode);

            if (responseCode < 200 || responseCode >= 300) {
                return "{\"error\":\"HTTP error code: " + responseCode + "\"}";
            }

            StringBuilder response = new StringBuilder();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            )) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }

            return response.toString();

        } catch (Exception e) {
            LOG.error("API call failed", e);
            return "{\"error\":\"API call failed: " + e.getMessage() + "\"}";
        }
    }



}