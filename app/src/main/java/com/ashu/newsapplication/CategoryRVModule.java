package com.ashu.newsapplication;

public class CategoryRVModule {
    private String categoryImageUrl;
    private String category;



    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryRVModule( String category) {
        this.category = category;
    }
}
