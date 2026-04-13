package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;
import java.util.List;

/**
 * Represents the main model interface for the Footer component.
 * <p>
 * This interface provides methods to retrieve footer-related data
 * such as logo, description, copyright, and structured footer links.
 * </p>
 * <p>
 * It extends {@link ComponentExporter} to support JSON export
 * for frontend applications like React.
 * </p>
 * @author Jaya Chandra Reddy
 */
public interface FooterModel extends ComponentExporter {
    /**
     * Returns the path or URL of the Lyca logo.
     * @return the logo path
     */
    public String getLycaLogo();

    /**
     * Returns the description text of the footer.
     * @return the footer description
     */
    public String getLycaDescription();

    /**
     * Returns the copyright text.
     * @return the copyright string
     */
    public String getCopyright();

    /**
     * Returns the list of footer columns.
     * @return list of {@link FooterColumn}
     */
    public List<FooterColumn> getFooterLinks();
}
