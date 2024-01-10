package org.uuzics.simplearchive.entity.response;

import lombok.Getter;
import lombok.Setter;

public class ArchiveDeleteResponse {
    @Getter @Setter
    private String status;
    @Getter @Setter
    private String message;
}
