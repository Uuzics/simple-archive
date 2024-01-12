package org.uuzics.simplearchive.entity.request;

import lombok.Getter;
import lombok.Setter;

public class ArchiveDeleteRequest {
    @Getter
    @Setter
    private long archiveId;
    @Getter
    @Setter
    private String archiveSlug;
}
