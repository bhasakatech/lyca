package com.adobe.aem.lyca.core.services;

import com.adobe.aem.lyca.core.beans.BlogData;
import java.util.List;

/**
 * Service interface for retrieving blog data from AEM Content Fragments.
 *
 * <p>
 * This service is responsible for fetching and transforming Content Fragment
 * data into {@link BlogData} objects that can be consumed by Sling Models
 * and exported to the frontend (SPA/React).
 * </p>
 *
 * <p>
 * The implementation typically reads Content Fragments from the given parent path,
 * extracts required fields, and maps them into a structured list of BlogData.
 * </p>
 *
 * @since 1.0
 */
public interface BlogService {

    /**
     * Retrieves a list of blogs from the specified parent Content Fragment path.
     *
     * @param parentPath the root path where blog Content Fragments are stored
     *                   (e.g., "/content/dam/lyca/blogs")
     * @return list of {@link BlogData} representing blog entries
     */
    List<BlogData> getBlogs(String parentPath);
}