package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;

@Model(
        adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class BestSimPlanItem {


    private String id;
    private String name;
    private String monthlyCost;
    private String data;
    private String minutes;
    private String texts;
    private String contract;
    private String annualPriceRise;
    private String freeEuRoaming;

    public BestSimPlanItem(){}

    public BestSimPlanItem(String id, String name, String monthlyCost, String data, String minutes, String texts, String contract, String annualPriceRise, String freeEuRoaming) {
        this.id = id;
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.data = data;
        this.minutes = minutes;
        this.texts = texts;
        this.contract = contract;
        this.annualPriceRise = annualPriceRise;
        this.freeEuRoaming = freeEuRoaming;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(String monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getAnnualPriceRise() {
        return annualPriceRise;
    }

    public void setAnnualPriceRise(String annualPriceRise) {
        this.annualPriceRise = annualPriceRise;
    }

    public String getFreeEuRoaming() {
        return freeEuRoaming;
    }

    public void setFreeEuRoaming(String freeEuRoaming) {
        this.freeEuRoaming = freeEuRoaming;
    }
}
