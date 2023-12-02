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
            	"status"	TEXT NOT NULL DEFAULT 'active',
            	PRIMARY KEY("id" AUTOINCREMENT)
            );
            """)
    void initArchiveTable();

    @Update("""
            CREATE TABLE IF NOT EXISTS "file" (
            	"id"	INTEGER NOT NULL,
            	"archive_id"	INTEGER NOT NULL,
            	"slug"	TEXT NOT NULL UNIQUE,
            	"name"	TEXT NOT NULL,
            	"status"	TEXT NOT NULL,
            	FOREIGN KEY("archive_id") REFERENCES "archive"("id"),
            	PRIMARY KEY("id" AUTOINCREMENT)
            );
            """)
    void initFileTable();
}
