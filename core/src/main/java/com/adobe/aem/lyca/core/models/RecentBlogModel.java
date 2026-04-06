package com.adobe.aem.lyca.core.models;

import com.adobe.cq.export.json.ComponentExporter;

public interface RecentBlogModel extends ComponentExporter {

    String getHeadingTitle();
    String getCfPath();
}