package org.uuzics.simplearchive.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.uuzics.simplearchive.entity.FileTreeItem;
import org.uuzics.simplearchive.service.DbInitService;

@Component
public class DbInitializer implements ApplicationRunner {
    @Autowired
    DbInitService dbInitService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileTreeItem rootDir = new FileTreeItem();
        rootDir.setId(0);
        rootDir.setParent(-1);
        rootDir.setType("dir");
        rootDir.setName("root");
        this.dbInitService.initFileTreeTable();
        this.dbInitService.initRootDir(rootDir);
    }
}
