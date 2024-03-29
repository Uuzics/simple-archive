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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.File;
import org.uuzics.simplearchive.service.ArchiveService;

import java.lang.reflect.Type;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private ArchiveService archiveService;

    @RequestMapping(value = "/dash", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleDashboard(Model model) {
        return "admin_dash";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleEditorNew(Model model) {
        return "admin_editor_new";
    }

    @RequestMapping(value = "/edit/{archiveId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleEditorEdit(@PathVariable("archiveId") long archiveId, Model model) {
        Archive archive = this.archiveService.adminGetArchiveById(archiveId);
        if (null != archive) {
            String fileListJsonObj = archive.getFileListJsonObj();
            Type fileListType = new TypeToken<List<File>>() {
            }.getType();
            List<File> fileList = new Gson().fromJson(fileListJsonObj, fileListType);
            model.addAttribute("fileList", fileList);
            model.addAttribute("archive", archive);
            return "admin_editor_edit";
        } else {
            return "not_found";
        }
    }

    @RequestMapping(value = "/view/{archiveId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleViewer(@PathVariable("archiveId") long archiveId, Model model) {
        Archive archive = this.archiveService.adminGetArchiveById(archiveId);
        if (null != archive) {
            String fileListJsonObj = archive.getFileListJsonObj();
            Type fileListType = new TypeToken<List<File>>() {
            }.getType();
            List<File> fileList = new Gson().fromJson(fileListJsonObj, fileListType);
            model.addAttribute("fileList", fileList);
            String prettyFileListJson = new GsonBuilder().setPrettyPrinting().create().toJson(fileList);
            archive.setFileListJsonObj(prettyFileListJson);
            if (null == archive.getTitle() || "".equals(archive.getTitle())) {
                archive.setTitle(String.format("[Untitled: %s]", archive.getSlug()));
            }
            model.addAttribute("archive", archive);
            return "admin_viewer";
        } else {
            return "not_found";
        }
    }

    @RequestMapping(value = "/delete/{archiveId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleDelete(@PathVariable("archiveId") long archiveId, Model model) {
        Archive archive = this.archiveService.adminGetArchiveById(archiveId);
        if (null != archive) {
            model.addAttribute("title", archive.getTitle());
            model.addAttribute("description", archive.getDescription());
            model.addAttribute("id", archiveId);
            model.addAttribute("slug", archive.getSlug());
            return "admin_delete";
        } else {
            return "not_found";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleSearch(@RequestParam("keyword") String keyword, Model model) {
        if (null != keyword && !("".equals(keyword))) {
            model.addAttribute("keyword", keyword);
            return "admin_search";
        } else {
            model.addAttribute("title", "Internal Error");
            model.addAttribute("heading", "Invalid search keyword");
            model.addAttribute("description", """
                    Please check search keyword.
                    """);
            return "custom_error";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleLogin() {
        return "admin_login";
    }
}
