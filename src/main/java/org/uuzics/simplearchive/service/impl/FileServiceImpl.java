package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.entity.File;
import org.uuzics.simplearchive.mapper.FileMapper;
import org.uuzics.simplearchive.service.FileService;

import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;

    @Override
    public List<File> getFileListByArchiveId(long archiveId) {
        return this.fileMapper.getFileListByArchiveId(archiveId);
    }
}
