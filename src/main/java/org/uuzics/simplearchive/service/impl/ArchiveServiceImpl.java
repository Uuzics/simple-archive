package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.entity.Archive;
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
    public List<Archive> adminGetPaginatedArchive(long limit, long offset) {
        return this.archiveMapper.adminGetPaginatedArchive(limit, offset);
    }

    @Override
    public long adminGetArchiveCount() {
        return this.archiveMapper.adminGetArchiveCount();
    }

    @Override
    public void adminSaveArchive(Archive archive) {
        this.archiveMapper.adminSaveArchive(archive);
    }

    @Override
    public long adminCheckArchiveSlug(String slug) {
        return this.archiveMapper.adminCheckArchiveSlug(slug);
    }
}
