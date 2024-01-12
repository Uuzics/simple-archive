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
package org.uuzics.simplearchive.entity;

import lombok.Getter;
import lombok.Setter;

public class Archive {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String slug;
    @Getter
    @Setter
    private String title; // Optional
    @Getter
    @Setter
    private String description; // Optional
    @Getter
    @Setter
    private String fileListJsonObj;
    @Getter
    @Setter
    private String status;

    public static Archive convertToDisplayArchive(Archive archive) {
        if (null == archive.getTitle() || "".equals(archive.getTitle())) {
            archive.setTitle(String.format("[Untitled: %s]", archive.getSlug()));
        }
        if (null == archive.getDescription() || "".equals(archive.getDescription())) {
            archive.setDescription("No description for this archive.");
        }
        archive.setId(-1);
        archive.setFileListJsonObj(null);
        archive.setStatus("display");
        return archive;
    }
}
