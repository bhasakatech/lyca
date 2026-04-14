package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

/**
 * Interface for the Lyca Mobile App Component Sling Model.
 *
 * <p>
 * This interface defines the contract for retrieving data from the
 * Lyca Mobile App Component used in the AEM application.
 * It extends {@link ComponentExporter} to support JSON export
 * through the Sling Model Exporter framework.
 * </p>
 *
 * <p>
 * The component provides:
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
 * @author
 * Adobe
 */

public interface LycaMobileAppComponentModel extends ComponentExporter {

    /**
     * Returns the main heading of the mobile app component.
     *
     * @return main heading string
     */
    public String getMainHeading();

    /**
     * Returns the list of mobile app features.
     *
     * @return list of feature descriptions
     */
    public List<String> getMobileAppFeatures();

    /**
     * Returns the list of app store components.
     *
     * @return list of app store items
     */
    public List<AppStore> getAppStoreComponents();

    /**
     * Returns the QR text displayed with the QR code.
     *
     * @return QR text string
     */
    public String getQRText();

    /**
     * Returns the QR code image path.
     *
     * @return QR image path
     */
    public String getQRImage();

    /**
     * Returns the mobile image path.
     *
     * @return mobile image path
     */
    public String getMobileImage();
}
