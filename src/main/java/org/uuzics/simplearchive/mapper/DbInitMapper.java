package org.uuzics.simplearchive.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.uuzics.simplearchive.entity.FileTreeItem;

@Mapper
@Repository
public interface DbInitMapper {
    @Update("""
            CREATE TABLE IF NOT EXISTS "file_tree" (
            	"id"	INTEGER NOT NULL,
            	"parent"	INTEGER NOT NULL,
            	"type"	TEXT NOT NULL,
            	"name"	TEXT NOT NULL,
            	PRIMARY KEY("id" AUTOINCREMENT),
            	UNIQUE("parent","name")
            );
            """)
    void initFileTreeTable();

    @Insert("""
            INSERT OR IGNORE INTO file_tree(id, parent, type, name)
            VALUES(#{id}, #{parent}, #{type}, #{name})
            """)
    void initRootDir(FileTreeItem rootDir);
}
