package main.java.PageObjects;

import main.java.Resources.Base;

public class PageUrl {

    private String url;
    private String description;

    public PageUrl(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return Base.getUrl() + url;
    }

    public String getDescription() {
        return description;
    }

}
