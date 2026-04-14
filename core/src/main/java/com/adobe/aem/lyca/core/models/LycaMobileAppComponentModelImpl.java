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

/**
 * Sling Model implementation for the Lyca Mobile App Component.
 *
 * <p>
 * This model adapts from both {@link SlingHttpServletRequest} and
 * {@link Resource}, and exports component data as JSON using the
 * Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The Lyca Mobile App Component provides:
 * </p>
 * <ul>
 *     <li>Main heading of the mobile app section</li>
 *     <li>List of mobile app feature descriptions</li>
 *     <li>List of app store download components</li>
 *     <li>QR code text description</li>
 *     <li>QR code image path</li>
 *     <li>Mobile device image path</li>
 * </ul>
 *
 * <p>
 * Resource type mapped:
 * {@value #RESOURCE_TYPE}
 * </p>
 *
 * @author
 * Adobe
 */

@Model(adaptables ={SlingHttpServletRequest.class, Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = LycaMobileAppComponentModelImpl.RESOURCE_TYPE,
        adapters = {LycaMobileAppComponentModel.class, ComponentExporter.class})
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class LycaMobileAppComponentModelImpl implements LycaMobileAppComponentModel{
    /**
     * Resource type for the Lyca Mobile App Component.
     */
    public static final String RESOURCE_TYPE = "lyca-spa-react/components/lycamobileappcomponent";

    /**
     * Main heading text of the mobile app component.
     */
    @ValueMapValue
    public String mainHeading;

    /**
     * List of mobile app feature descriptions.
     */
    @ValueMapValue
    public List<String> mobileAppFeatures;

    /**
     * List of app store download components.
     */
    @ChildResource
    public List<AppStore> appStore;

    /**
     * Text displayed alongside the QR code.
     */
    @ValueMapValue
    public String qrText;

    /**
     * Path to the QR code image.
     */
    @ValueMapValue
    public String qrImage;

    /**
     * Path to the mobile device image.
     */
    @ValueMapValue
    public String mobileImage;


    /**
     * Returns the main heading of the mobile app component.
     *
     * @return main heading string
     */
    @Override
    public String getMainHeading() {
        return mainHeading;
    }

    /**
     * Returns the list of mobile app feature descriptions.
     *
     * @return list of mobile app features
     */
    @Override
    public List<String> getMobileAppFeatures() {
        return mobileAppFeatures;
    }

    /**
     * Returns the list of app store download components.
     *
     * @return list of app store items
     */
    @Override
    public List<AppStore> getAppStoreComponents() {
        return appStore;
    }

    /**
     * Returns the QR text displayed with the QR code.
     *
     * @return QR text string
     */
    @Override
    public String getQRText() {
        return qrText;
    }

    /**
     * Returns the QR code image path.
     *
     * @return QR image path
     */
    @Override
    public String getQRImage() {
        return qrImage;
    }


    /**
     * Returns the mobile image path.
     *
     * @return mobile image path
     */
    @Override
    public String getMobileImage() {
        return mobileImage;
    }

    /**
     * Returns the exported resource type of this component.
     *
     * @return exported resource type string
     */
    @Override
    public String getExportedType() {
        return LycaMobileAppComponentModelImpl.RESOURCE_TYPE;
    }
}
