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

    public static Archive completeOptionalField(Archive archive) {
        if (null == archive.getTitle() || "".equals(archive.getTitle())) {
            archive.setTitle(String.format("[Untitled: %s]", archive.getSlug()));
        }
        if (null == archive.getDescription() || "".equals(archive.getDescription())) {
            archive.setDescription("No description for this archive.");
        }
        return archive;
    }
}
