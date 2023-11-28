package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.mapper.ArchiveMapper;
import org.uuzics.simplearchive.service.ArchiveService;

@Service("archiveService")
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public Archive getArchiveBySlug(String slug) {
        return this.archiveMapper.getArchiveBySlug(slug);
    }
}
