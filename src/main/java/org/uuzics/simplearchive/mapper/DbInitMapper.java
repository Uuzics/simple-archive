/*
 * Copyright 2024 Uuzics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
