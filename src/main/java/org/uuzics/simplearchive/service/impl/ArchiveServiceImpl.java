package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.FrontendListedArchive;
import org.uuzics.simplearchive.mapper.ArchiveMapper;
import org.uuzics.simplearchive.service.ArchiveService;

import java.util.List;

@Service("archiveService")
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public Archive getArchiveBySlug(String slug) {
        return this.archiveMapper.getArchiveBySlug(slug);
    }

    @Override
    public Archive adminGetArchiveBySlug(String slug) {
        return this.archiveMapper.adminGetArchiveBySlug(slug);
    }

    @Override
    public Archive adminGetArchiveById(long id) {
        return this.archiveMapper.adminGetArchiveById(id);
    }

    @Override
    public List<FrontendListedArchive> adminGetPaginatedArchive(long limit, long offset) {
        List<FrontendListedArchive> archiveList = this.archiveMapper.adminGetPaginatedArchive(limit, offset);
        for (FrontendListedArchive archiveItem : archiveList) {
            if (null == archiveItem.getArchiveName() || "".equals(archiveItem.getArchiveName())) {
                archiveItem.setArchiveName(String.format("[Untitled: %s]", archiveItem.getArchiveSlug()));
            }
        }
        return archiveList;
    }

    @Override
    public long adminGetArchiveCount() {
        return this.archiveMapper.adminGetArchiveCount();
    }

    @Override
    public void adminEditArchive(Archive archive) {
        this.archiveMapper.adminEditArchive(archive);
    }

    public void adminNewArchive(Archive archive) {
        this.archiveMapper.adminNewArchive(archive);
    }

    @Override
    public long adminCheckArchiveSlug(String slug) {
        return this.archiveMapper.adminCheckArchiveSlug(slug);
    }
}
