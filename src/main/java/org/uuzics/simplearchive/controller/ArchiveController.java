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
package org.uuzics.simplearchive.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.File;
import org.uuzics.simplearchive.service.ArchiveService;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;

    @RequestMapping(value = "/archive/{archiveSlug}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleGetArchive(@PathVariable("archiveSlug") String archiveSlug, Model model) {
        Archive archive = this.archiveService.getArchiveBySlug(archiveSlug);
        if (null != archive) {
            String fileListJsonObj = archive.getFileListJsonObj();
            model.addAttribute("archive", Archive.convertToDisplayArchive(archive));
            Type fileListType = new TypeToken<List<File>>() {
            }.getType();
            List<File> fileList = new Gson().fromJson(fileListJsonObj, fileListType);
            model.addAttribute("fileList", fileList);
            return "archive";
        } else {
            return "not_found";
        }
    }
}
