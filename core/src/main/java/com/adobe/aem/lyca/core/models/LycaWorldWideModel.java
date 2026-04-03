package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

import java.util.List;

public interface LycaWorldWideModel extends ComponentExporter {

    String getWorldwideHeading();

    String getWorldwideSubHeading();

    String getSearchPlaceholderText();

    List<CountryModel> getCountries();

    String getCtaLabel();

    String getCtaLink();
}