package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class Archive {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String slug;
    @Getter @Setter
    private String title; // Optional
    @Getter @Setter
    private String description; // Optional
    @Getter @Setter
    private String fileListJsonObj;
    @Getter @Setter
    private String status;

    public static Archive convertToDisplayArchive(Archive archive) {
        if (null == archive.getTitle() || "".equals(archive.getTitle())) {
            archive.setTitle(String.format("[Untitled: %s]", archive.getSlug()));
        }
        if (null == archive.getDescription() || "".equals(archive.getDescription())) {
            archive.setDescription("No description for this archive.");
        }
        archive.setId(-1);
        archive.setFileListJsonObj(null);
        archive.setStatus("display");
        return archive;
    }
}
