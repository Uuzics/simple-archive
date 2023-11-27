package org.uuzics.simplearchive.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DbInitMapper {
    @Update("""
            CREATE TABLE IF NOT EXISTS "file_tree" (
            	"id"	INTEGER NOT NULL,
            	"parent"	INTEGER NOT NULL,
            	"name"	TEXT NOT NULL,
            	PRIMARY KEY("id" AUTOINCREMENT),
            	UNIQUE("parent","name")
            );
            """)
    void initFileTreeTable();
}
