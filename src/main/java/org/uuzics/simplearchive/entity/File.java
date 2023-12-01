package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class File {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private long archiveId;
    @Getter @Setter
    private String slug;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String status;
}
