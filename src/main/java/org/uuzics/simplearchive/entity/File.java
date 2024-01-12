package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class File {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String url;

    public File() {
    }

    public File(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
