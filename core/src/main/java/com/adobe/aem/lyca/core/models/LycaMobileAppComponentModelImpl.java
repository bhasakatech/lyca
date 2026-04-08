package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;

@Model(adaptables ={SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = LycaMobileAppComponentModelImpl.RESOURCE_TYPE,
        adapters = {LycaMobileAppComponentModel.class, ComponentExporter.class})
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LycaMobileAppComponentModelImpl implements LycaMobileAppComponentModel{
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycamobileappcomponent";

    @ValueMapValue
    public String mainHeading;

    @ValueMapValue
    public List<String> mobileAppFeatures;

    @ChildResource
    public List<AppStore> appStore;

    @ValueMapValue
    public String qrText;

    @ValueMapValue
    public String qrImage;

    @ValueMapValue
    public String mobileImage;

    @Override
    public String getMainHeading() {
        return mainHeading;
    }

    @Override
    public List<String> getMobileAppFeatures() {
        return mobileAppFeatures;
    }

    @Override
    public List<AppStore> getAppStoreComponents() {
        return appStore;
    }

    @Override
    public String getQRText() {
        return qrText;
    }

    @Override
    public String getQRImage() {
        return qrImage;
    }

    @Override
    public String getMobileImage() {
        return mobileImage;
    }

    @Override
    public String getExportedType() {
        return LycaMobileAppComponentModelImpl.RESOURCE_TYPE;
    }
}
