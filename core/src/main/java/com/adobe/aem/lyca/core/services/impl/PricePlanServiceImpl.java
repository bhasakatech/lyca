package com.adobe.aem.lyca.core.services.impl;

import com.adobe.aem.lyca.core.models.PricePlan;
import com.adobe.aem.lyca.core.models.PricePlanCFModel;
import com.adobe.aem.lyca.core.services.PricePlanService;

import com.adobe.aem.lyca.core.utils.NPUtilService;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.*;

import javax.jcr.query.Query;
import java.util.*;

@Component(service = PricePlanService.class, immediate = true)
public class PricePlanServiceImpl implements PricePlanService {

    @Reference
    private NPUtilService npUtilService;

    @Override
    public List<PricePlan> getPricePlans(String damPath) {

        List<PricePlan> plans = new ArrayList<>();

        try (ResourceResolver resolver = npUtilService.getResourceResolver()) {

            String query = "SELECT * FROM [dam:Asset] AS s " +
                    "WHERE ISDESCENDANTNODE(s, '" + damPath + "')";

            Iterator<Resource> results = resolver.findResources(query,Query.JCR_SQL2);

            while (results.hasNext()) {

                Resource assetResource = results.next();

                Resource dataResource = assetResource.getChild("jcr:content/data/master");

                if (dataResource == null) {
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
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plans;
    }
}