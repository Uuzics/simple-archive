package org.uuzics.simplearchive.entity.response;

import lombok.Getter;
import lombok.Setter;
import org.uuzics.simplearchive.entity.FrontendListedArchive;

import java.util.List;

public class PaginatedArchiveListResponse {
    @Getter @Setter
    private long pageCount;
    @Getter @Setter
    private List<FrontendListedArchive> archiveList;
}
