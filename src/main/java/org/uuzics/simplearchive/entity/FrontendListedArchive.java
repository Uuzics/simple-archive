package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class FrontendListedArchive {
    @Getter
    @Setter
    private String archiveName;
    @Getter
    @Setter
    private String archiveSlug;
    @Getter
    @Setter
    private long archiveId;
}
