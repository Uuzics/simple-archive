package org.uuzics.simplearchive.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DbInitMapper {

    @Update("""
            CREATE TABLE IF NOT EXISTS "archive" (
            	"id"	INTEGER NOT NULL,
            	"slug"	TEXT NOT NULL UNIQUE,
            	"title"	TEXT,
            	"description"	TEXT,
            	"file_list"	TEXT DEFAULT '[]',
            	"status"	TEXT NOT NULL DEFAULT 'active',
            	PRIMARY KEY("id" AUTOINCREMENT)
            );
            """)
    void initArchiveTable();
}
