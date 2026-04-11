package com.adobe.aem.lyca.core.services;

import com.adobe.aem.lyca.core.beans.BlogData;

import java.util.List;

public interface BlogService {
    List<BlogData> getBlogs(String parentPath);
}