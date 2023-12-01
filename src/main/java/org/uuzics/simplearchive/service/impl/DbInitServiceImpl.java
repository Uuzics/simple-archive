package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.mapper.DbInitMapper;
import org.uuzics.simplearchive.service.DbInitService;

@Service("dbInitService")
public class DbInitServiceImpl implements DbInitService {
    @Autowired
    private DbInitMapper dbInitMapper;

    @Override
    public void initArchiveTable() {
        this.dbInitMapper.initArchiveTable();
    }

    @Override
    public void initFileTable() {
        this.dbInitMapper.initFileTable();
    }
}
