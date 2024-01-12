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
package org.uuzics.simplearchive.service;

import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.FrontendListedArchive;

import java.util.List;

public interface ArchiveService {
    Archive getArchiveBySlug(String slug);

    Archive adminGetArchiveBySlug(String slug);

    Archive adminGetArchiveById(long id);

    List<FrontendListedArchive> adminGetPaginatedArchive(long limit, long offset);

    long adminGetArchiveCount();

    long adminSearchArchiveCount(String keyword);

    void adminEditArchive(Archive archive);

    void adminNewArchive(Archive archive);

    long adminCheckArchiveSlug(String slug);

    void adminDeleteArchiveById(long id);

    List<FrontendListedArchive> adminSearchPaginatedArchive(long limit, long offset, String keyword);
}
