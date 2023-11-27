package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class FileTreeItem {
    @Getter @Setter
    private long id;

    @Getter @Setter
    private long parent;

    @Getter @Setter
    private String type;

    @Getter @Setter
    private String name;
}
