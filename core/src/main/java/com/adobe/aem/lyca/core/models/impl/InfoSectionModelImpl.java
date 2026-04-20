package com.adobe.aem.lyca.core.models.impl;

import com.adobe.aem.lyca.core.models.FooterLink;
import com.adobe.aem.lyca.core.models.InfoSectionModel;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


/**
 * Implementation class for {@link InfoSectionModel}.
 * <p>
 * This Sling Model adapts from {@link Resource}
 * this is giving heading and description for general information
 * </p>
 * <p>
 * The properties are injected using
 * {@link ValueMapValue}.
 * </p>
 */
@Model(
        adaptables ={Resource.class, SlingHttpServletRequest.class},
        adapters = {InfoSectionModel.class, ComponentExporter.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = InfoSectionModelImpl.RESOURCE_TYPE
)
@Exporter(
       name =  ExporterConstants.SLING_MODEL_EXPORTER_NAME ,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class InfoSectionModelImpl implements InfoSectionModel {

    public  static final String RESOURCE_TYPE = "lyca-spa-react/components/content/info-section";

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String description;

    /**
     * Returns the exported resource type.
     * @return the resource type
     */
    @Override
    public String getExportedType() {
        return InfoSectionModelImpl.RESOURCE_TYPE;
    }


    /**
     *
     * @return heading
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     *
     * @return description
     */
    @Override
    public String getDescription() {
        return description;
    }
}
