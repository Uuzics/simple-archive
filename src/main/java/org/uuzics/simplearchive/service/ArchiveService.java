package org.uuzics.simplearchive.service;

import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.FrontendListedArchive;

import java.util.List;

public interface ArchiveService {
    Archive getArchiveBySlug(String slug);

    Archive adminGetArchiveBySlug(String slug);

    Archive adminGetArchiveById(long id);

    List<FrontendListedArchive> adminGetPaginatedArchive(long limit, long offset);

    long adminGetArchiveCount();

    long adminSearchArchiveCount(String keyword);

    void adminEditArchive(Archive archive);

    void adminNewArchive(Archive archive);

    long adminCheckArchiveSlug(String slug);

    void adminDeleteArchiveById(long id);

    List<FrontendListedArchive> adminSearchPaginatedArchive(long limit, long offset, String keyword);
}
