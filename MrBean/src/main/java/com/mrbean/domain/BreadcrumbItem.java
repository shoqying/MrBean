package com.mrbean.domain;

public class BreadcrumbItem {
    private String label;
    private String link;
    private boolean active;

    public BreadcrumbItem(String label, String link, boolean active) {
        this.label = label;
        this.link = link;
        this.active = active;
    }

    public String getLabel() {
        return label;
    }
    public String getLink() {
        return link;
    }
    public boolean isActive() {
        return active;
    }
}
