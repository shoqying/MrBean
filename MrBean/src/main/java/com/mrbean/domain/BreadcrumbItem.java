package com.mrbean.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BreadcrumbItem {
    private String label;
    private String link;
    private boolean active;

    public BreadcrumbItem(String label, String link, boolean active) {
        this.label = label;
        this.link = link;
        this.active = active;
    }
}
