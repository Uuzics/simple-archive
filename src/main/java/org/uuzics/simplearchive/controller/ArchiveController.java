package org.uuzics.simplearchive.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.service.ArchiveService;

@Controller
public class ArchiveController {
    @Resource
    private ArchiveService archiveService;

    @RequestMapping(value = "/archive/{archiveSlug}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String helloWorld(@PathVariable("archiveSlug") String archiveSlug, Model model) {
        Archive archive = this.archiveService.getArchiveBySlug(archiveSlug);
        if (null != archive) {
            model.addAttribute("archive", Archive.completeOptionalField(archive));
            return "archive";
        } else {
            return "not_found";
        }
    }
}
