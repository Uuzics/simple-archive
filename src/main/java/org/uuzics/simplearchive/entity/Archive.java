package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class Archive {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String slug;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String status;
}
