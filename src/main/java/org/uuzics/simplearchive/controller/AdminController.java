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
    public String handleDashboard(Model model){
        return "admin_dash";
    }
    @RequestMapping(value = "/new", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleEditor(Model model){
        return "admin_editor";
    }

    @RequestMapping(value = "/view/{archiveSlug}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleViewer(@PathVariable("archiveSlug") String archiveSlug, Model model) {
        Archive archive = this.archiveService.adminGetArchiveBySlug(archiveSlug);
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
}
