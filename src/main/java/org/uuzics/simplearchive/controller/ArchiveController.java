package org.uuzics.simplearchive.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.DisplayedFile;
import org.uuzics.simplearchive.service.ArchiveService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;

    @RequestMapping(value = "/archive/{archiveSlug}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String handleGetArchive(@PathVariable("archiveSlug") String archiveSlug, Model model) {
        Archive archive = this.archiveService.getArchiveBySlug(archiveSlug);
        if (null != archive) {
            // TODO archive and file list
            model.addAttribute("archive", Archive.completeOptionalField(archive));
            List<DisplayedFile> displayedFileList = new ArrayList<>();
            model.addAttribute("fileList", displayedFileList);
            return "archive";
        } else {
            return "not_found";
        }
    }
}
