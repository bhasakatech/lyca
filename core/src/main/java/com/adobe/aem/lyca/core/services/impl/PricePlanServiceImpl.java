package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanCFModel;
import com.adobe.aem.lyca.core.services.PricePlanService;
import com.adobe.aem.lyca.core.utils.NPUtilService;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementation of {@link PricePlanService}.
 *
 * <p>
 * This service retrieves Price Plan data from DAM (Content Fragments),
 * adapts them into {@link PricePlanCFModel}, and maps them to
 * {@link PricePlan} POJO objects for further use in Sling Models or UI.
 * </p>
 */
@Component(service = PricePlanService.class, immediate = true)
public class PricePlanServiceImpl implements PricePlanService {
    /**
     * Logger instance for logging debug and error messages.
     */
    private static final Logger LOG = LoggerFactory.getLogger(PricePlanServiceImpl.class);

    /**
     * Utility service to obtain service resource resolver.
     */
    @Reference
    private NPUtilService npUtilService;

    /**
     * Fetches price plans from the given DAM path.
     *
     * <p>
     * This method performs the following steps:
     * <ul>
     *     <li>Gets a service resource resolver</li>
     *     <li>Executes a JCR-SQL2 query to fetch DAM assets</li>
     *     <li>Adapts each asset to {@link PricePlanCFModel}</li>
     *     <li>Maps the data to {@link PricePlan} POJO</li>
     * </ul>
     * </p>
     *
     * @param damPath the DAM path containing price plan content fragments
     * @return list of {@link PricePlan} objects
     *
     */
    @Override
    public List<PricePlan> getPricePlans(String damPath) {

        List<PricePlan> plans = new ArrayList<>();

        try (ResourceResolver resolver = npUtilService.getResourceResolver()) {

            LOG.debug("Starting to fetch price plans from path: {}", damPath);

            // JCR-SQL2 query to fetch DAM assets under the given path
            String query = "SELECT * FROM [dam:Asset] AS s " +
                    "WHERE ISDESCENDANTNODE(s, '" + damPath + "')";
            if(damPath!=null) {
                Iterator<Resource> results = resolver.findResources(query, Query.JCR_SQL2);

                while (results.hasNext()) {

                    Resource assetResource = results.next();

                    // Navigate to Content Fragment data node
                    Resource dataResource = assetResource.getChild("jcr:content/data/master");

                    if (dataResource == null) {
                        LOG.warn("data/master node missing for asset: {}", assetResource.getPath());
                        continue;
                    }
                    // Adapt resource to Sling Model
                    PricePlanCFModel cfModel = dataResource.adaptTo(PricePlanCFModel.class);

                    if (cfModel != null) {

                        PricePlan plan = new PricePlan();
                        // Map CF model to POJO
                        plan.setPlanTitle(cfModel.getPlanTitle());
                        plan.setPriceMonthly(cfModel.getPriceMonthly());
                        plan.setPriceYearly(cfModel.getPriceYearly());
                        plan.setDataLimit(cfModel.getDataLimit());
                        plan.setFeatures(cfModel.getFeatures());
                        plan.setPopular(cfModel.isPopular());
                        plan.setCtaLabel(cfModel.getCtaLabel());
                        plan.setCtaLink(cfModel.getCtaLink());

                        plans.add(plan);

                    } else {
                        LOG.warn("Failed to adapt resource to PricePlanCFModel: {}", dataResource.getPath());
                    }
                }
            }
            LOG.info("Successfully fetched {} price plans from {}", plans.size(), damPath);

        }
        catch (RepositoryException e) {
            LOG.error("RepositoryException while querying DAM path: {}", damPath, e);

        }

        return plans;
    }
}