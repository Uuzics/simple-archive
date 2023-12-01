package org.uuzics.simplearchive.service;

import org.uuzics.simplearchive.entity.File;

import java.util.List;

public interface FileService {
    List<File> getFileListByArchiveId(long archiveId);
}
