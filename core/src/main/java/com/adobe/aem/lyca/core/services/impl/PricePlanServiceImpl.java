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

@Component(service = PricePlanService.class, immediate = true)
public class PricePlanServiceImpl implements PricePlanService {

    private static final Logger LOG = LoggerFactory.getLogger(PricePlanServiceImpl.class);

    @Reference
    private NPUtilService npUtilService;

    @Override
    public List<PricePlan> getPricePlans(String damPath) {

        List<PricePlan> plans = new ArrayList<>();

        try (ResourceResolver resolver = npUtilService.getResourceResolver()) {

            LOG.debug("Starting to fetch price plans from path: {}", damPath);

            String query = "SELECT * FROM [dam:Asset] AS s " +
                    "WHERE ISDESCENDANTNODE(s, '" + damPath + "')";

            Iterator<Resource> results = resolver.findResources(query, Query.JCR_SQL2);

            while (results.hasNext()) {

                Resource assetResource = results.next();

                Resource dataResource = assetResource.getChild("jcr:content/data/master");

                if (dataResource == null) {
                    LOG.warn("data/master node missing for asset: {}", assetResource.getPath());
                    continue;
                }

                PricePlanCFModel cfModel = dataResource.adaptTo(PricePlanCFModel.class);

                if (cfModel != null) {

                    PricePlan plan = new PricePlan();

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

            LOG.info("Successfully fetched {} price plans from {}", plans.size(), damPath);

        } catch (RepositoryException e) {
            LOG.error("RepositoryException while querying DAM path: {}", damPath, e);

        } catch (Exception e) {
            LOG.error("Unexpected error while fetching price plans for path: {}", damPath, e);
        }

        return plans;
    }
}