package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.entity.FileTreeItem;
import org.uuzics.simplearchive.mapper.DbInitMapper;
import org.uuzics.simplearchive.service.DbInitService;

@Service("dbInitService")
public class DbInitServiceImpl implements DbInitService {
    @Autowired
    private DbInitMapper dbInitMapper;

    @Override
    public void initFileTreeTable() {
        this.dbInitMapper.initFileTreeTable();
    }

    @Override
    public void initRootDir(FileTreeItem rootDir) {
        this.dbInitMapper.initRootDir(rootDir);
    }
}
