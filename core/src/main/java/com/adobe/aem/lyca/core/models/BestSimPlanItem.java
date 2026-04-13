package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;

/**
 * Model class representing a single SIM Plan item.
 *
 * <p>
 * This class is used as a data transfer object (DTO) for individual SIM plans.
 * It is typically populated from Content Fragment data and exposed to the
 * front-end (SPA/React) via JSON export.
 * </p>
 *
 * <p>
 * Each instance contains details such as pricing, data allowance,
 * calling minutes, contract information, and additional benefits.
 * </p>
 *
 * <p>
 * This model can be adapted from a {@link Resource} if needed, but is
 * primarily constructed programmatically in the parent model
 * ({@code BestSimPlansModelImpl}).
 * </p>
 */
@Model(
        adaptables = {Resource.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(
        name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
        extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class BestSimPlanItem {

    /**
     * Unique identifier of the SIM plan (typically derived from resource name).
     */
    private String id;

    /**
     * Name/title of the SIM plan.
     */
    private String name;

    /**
     * Monthly cost of the SIM plan.
     */
    private String monthlyCost;

    /**
     * Data allowance included in the plan (e.g., "10GB", "Unlimited").
     */
    private String data;

    /**
     * Number of call minutes included in the plan.
     */
    private String minutes;

    /**
     * Number of text messages included in the plan.
     */
    private String texts;

    /**
     * Contract duration or type (e.g., "12 months", "No contract").
     */
    private String contract;

    /**
     * Information about annual price increase, if applicable.
     */
    private String annualPriceRise;

    /**
     * Indicates whether free EU roaming is included in the plan.
     */
    private String freeEuRoaming;

    /**
     * Default constructor required for serialization/deserialization.
     */
    public BestSimPlanItem() {}

    /**
     * Parameterized constructor to create a SIM plan item with all attributes.
     *
     * @param id unique identifier of the plan
     * @param name plan name
     * @param monthlyCost monthly cost of the plan
     * @param data data allowance
     * @param minutes call minutes included
     * @param texts text messages included
     * @param contract contract details
     * @param annualPriceRise annual price increase details
     * @param freeEuRoaming EU roaming availability
     */
    public BestSimPlanItem(String id, String name, String monthlyCost, String data,
                          String minutes, String texts, String contract,
                          String annualPriceRise, String freeEuRoaming) {
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

    /**
     * @return unique identifier of the SIM plan
     */
    public String getId() {
        return id;
    }

    /**
     * @param id unique identifier of the SIM plan
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name of the SIM plan
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of the SIM plan
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return monthly cost of the plan
     */
    public String getMonthlyCost() {
        return monthlyCost;
    }

    /**
     * @param monthlyCost monthly cost of the plan
     */
    public void setMonthlyCost(String monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    /**
     * @return data allowance of the plan
     */
    public String getData() {
        return data;
    }

    /**
     * @param data data allowance of the plan
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return included call minutes
     */
    public String getMinutes() {
        return minutes;
    }

    /**
     * @param minutes call minutes included
     */
    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    /**
     * @return included text messages
     */
    public String getTexts() {
        return texts;
    }

    /**
     * @param texts text messages included
     */
    public void setTexts(String texts) {
        this.texts = texts;
    }

    /**
     * @return contract details
     */
    public String getContract() {
        return contract;
    }

    /**
     * @param contract contract details
     */
    public void setContract(String contract) {
        this.contract = contract;
    }

    /**
     * @return annual price rise information
     */
    public String getAnnualPriceRise() {
        return annualPriceRise;
    }

    /**
     * @param annualPriceRise annual price rise information
     */
    public void setAnnualPriceRise(String annualPriceRise) {
        this.annualPriceRise = annualPriceRise;
    }

    /**
     * @return EU roaming availability
     */
    public String getFreeEuRoaming() {
        return freeEuRoaming;
    }

    /**
     * @param freeEuRoaming EU roaming availability
     */
    public void setFreeEuRoaming(String freeEuRoaming) {
        this.freeEuRoaming = freeEuRoaming;
    }
}