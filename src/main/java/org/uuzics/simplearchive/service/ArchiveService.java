package org.uuzics.simplearchive.service;

import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.FrontendListedArchive;

import java.util.List;

public interface ArchiveService {
    Archive getArchiveBySlug(String slug);

    List<FrontendListedArchive> adminGetPaginatedArchive(long limit, long offset);

    long adminGetArchiveCount();

    void adminSaveArchive(Archive archive);

    long adminCheckArchiveSlug(String slug);
}
