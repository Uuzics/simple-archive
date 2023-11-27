package org.uuzics.simplearchive.service;

import org.uuzics.simplearchive.entity.FileTreeItem;

public interface DbInitService {
    void initFileTreeTable();

    void initRootDir(FileTreeItem rootDir);
}
