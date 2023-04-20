package com.ashu.newsapplication;

public class CategoryRVModule {
    private String categoryImageUrl;
    private String category;

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryRVModule(String categoryImageUrl, String category) {
        this.categoryImageUrl = categoryImageUrl;
        this.category = category;
    }
}
