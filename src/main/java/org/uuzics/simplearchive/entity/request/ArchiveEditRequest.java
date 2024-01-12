package org.uuzics.simplearchive.entity.request;

import lombok.Getter;
import lombok.Setter;
import org.uuzics.simplearchive.entity.FrontendFile;

import java.util.List;

public class ArchiveEditRequest {
    @Getter
    @Setter
    private long archiveId;
    @Getter
    @Setter
    private String archiveName;
    @Getter
    @Setter
    private String archiveSlug;
    @Getter
    @Setter
    private String archiveStatus;
    @Getter
    @Setter
    private List<FrontendFile> fileList;
    @Getter
    @Setter
    private String archiveDescription;
}
