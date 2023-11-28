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
            	"path"	TEXT NOT NULL,
            	PRIMARY KEY("id" AUTOINCREMENT),
            	UNIQUE("parent","name"),
            	UNIQUE("path")
            );
            """)
    void initFileTreeTable();

    @Insert("""
            INSERT OR IGNORE INTO file_tree(id, parent, type, name, path)
            VALUES(#{id}, #{parent}, #{type}, #{name}, #{path})
            """)
    void initRootDir(FileTreeItem rootDir);

    @Update("""
            CREATE TABLE IF NOT EXISTS "archive" (
            	"id"	INTEGER NOT NULL,
            	"slug"	TEXT NOT NULL UNIQUE,
            	"title"	TEXT,
            	"description"	TEXT,
            	"status"	TEXT NOT NULL DEFAULT 'active',
            	PRIMARY KEY("id" AUTOINCREMENT)
            );
            """)
    void initArchiveTable();
}
