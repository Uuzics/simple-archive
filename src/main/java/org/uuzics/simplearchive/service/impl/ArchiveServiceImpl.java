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
package org.uuzics.simplearchive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.FrontendListedArchive;
import org.uuzics.simplearchive.mapper.ArchiveMapper;
import org.uuzics.simplearchive.service.ArchiveService;

import java.util.List;

@Service("archiveService")
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private ArchiveMapper archiveMapper;

    @Override
    public Archive getArchiveBySlug(String slug) {
        return this.archiveMapper.getArchiveBySlug(slug);
    }

    @Override
    public Archive adminGetArchiveBySlug(String slug) {
        return this.archiveMapper.adminGetArchiveBySlug(slug);
    }

    @Override
    public Archive adminGetArchiveById(long id) {
        return this.archiveMapper.adminGetArchiveById(id);
    }

    @Override
    public List<FrontendListedArchive> adminGetPaginatedArchive(long limit, long offset) {
        List<FrontendListedArchive> archiveList = this.archiveMapper.adminGetPaginatedArchive(limit, offset);
        for (FrontendListedArchive archiveItem : archiveList) {
            if (null == archiveItem.getArchiveName() || "".equals(archiveItem.getArchiveName())) {
                archiveItem.setArchiveName(String.format("[Untitled: %s]", archiveItem.getArchiveSlug()));
            }
        }
        return archiveList;
    }

    @Override
    public long adminGetArchiveCount() {
        return this.archiveMapper.adminGetArchiveCount();
    }

    @Override
    public long adminSearchArchiveCount(String keyword) {
        return this.archiveMapper.adminSearchArchiveCount(keyword);
    }

    @Override
    public void adminEditArchive(Archive archive) {
        this.archiveMapper.adminEditArchive(archive);
    }

    public void adminNewArchive(Archive archive) {
        this.archiveMapper.adminNewArchive(archive);
    }

    @Override
    public long adminCheckArchiveSlug(String slug) {
        return this.archiveMapper.adminCheckArchiveSlug(slug);
    }

    @Override
    public void adminDeleteArchiveById(long id) {
        this.archiveMapper.adminDeleteArchiveById(id);
    }

    @Override
    public List<FrontendListedArchive> adminSearchPaginatedArchive(long limit, long offset, String keyword) {
        return this.archiveMapper.adminSearchPaginatedArchive(limit, offset, keyword);
    }
}
