package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.osgiconfig.ApiContentTableConfig;
import com.adobe.aem.lyca.core.services.ApiContentTableService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.settings.SlingSettingsService;
import org.json.JSONArray;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.io.IOException;

@Component(service = ApiContentTableService.class, immediate = true)
@Designate(ocd = ApiContentTableConfig.class)
public class ApiContentTableServiceImpl implements ApiContentTableService {

    private String apiUrl;
    private boolean enabled;

    @Reference
    private SlingSettingsService slingSettingsService;

    @Activate
    protected void activate(ApiContentTableConfig config) {
        this.apiUrl = config.apiUrl();
        this.enabled = config.enabled();
    }

    @Override
    public JSONArray fetchData() {

        if (!enabled || apiUrl == null || apiUrl.isEmpty()) {
            return new JSONArray();
        }

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(apiUrl);
            CloseableHttpResponse response = client.execute(request);

            String json = EntityUtils.toString(response.getEntity());

            return new JSONArray(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }
}
