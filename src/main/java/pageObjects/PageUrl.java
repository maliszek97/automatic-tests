package main.java.pageObjects;

import main.java.Resources.Base;

public class PageUrl {

    private String viewUrl;
    private String description;

    public PageUrl(String viewUrl, String description) {
        this.viewUrl = viewUrl;
        this.description = description;
    }

    public String getUrl() {
        return Base.getUrl() + viewUrl;
    }

    public String getDescription() {
        return description;
    }

}
