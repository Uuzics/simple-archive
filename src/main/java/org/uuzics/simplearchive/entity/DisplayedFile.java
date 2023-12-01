package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class DisplayedFile {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String url;

    public DisplayedFile() {
    }

    public DisplayedFile(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
