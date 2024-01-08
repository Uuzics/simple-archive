package org.uuzics.simplearchive.controller;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.File;
import org.uuzics.simplearchive.entity.FrontendFile;
import org.uuzics.simplearchive.entity.request.ArchiveEditRequest;
import org.uuzics.simplearchive.entity.response.ArchiveEditResponse;
import org.uuzics.simplearchive.entity.response.ArchiveSlugCheckResponse;
import org.uuzics.simplearchive.entity.response.PaginatedArchiveListResponse;
import org.uuzics.simplearchive.service.ArchiveService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/admin/api")
public class AdminApiController {
    @Resource
    private ArchiveService archiveService;

    @RequestMapping(value = "/save_archive", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ArchiveEditResponse handleArchiveEdit(@RequestBody ArchiveEditRequest archiveEditRequest) {
        ArchiveEditResponse response = new ArchiveEditResponse();
        response.setStatus("fail");
        response.setMessage("Archive was not saved.");
        try {
            String title = archiveEditRequest.getArchiveName();
            String slug = archiveEditRequest.getArchiveSlug();
            String status = archiveEditRequest.getArchiveStatus();
            String description = archiveEditRequest.getArchiveDescription();
            List<FrontendFile> fileList = archiveEditRequest.getFileList();
            boolean flag = true;
            // Check slug
            Pattern slugPattern = Pattern.compile("^[a-z0-9\\-]+$");
            if (!slugPattern.matcher(slug).matches()) {
                flag = false;
                response.setStatus("fail");
                response.setMessage("Invalid slug. Slug should only contain lowercase letters and hyphens.");
            }
            // Check status
            if (flag && !("active".equals(status) || "hidden".equals(status))) {
                flag = false;
                response.setStatus("fail");
                response.setMessage("Invalid status.");
            }
            // Check URL
            if (flag) {
                Pattern urlPattern = Pattern.compile("^(http|https)://.*$");
                for (FrontendFile frontendFile : fileList) {
                    if (!(urlPattern.matcher(frontendFile.getFileUrl()).matches())) {
                        flag = false;
                        response.setStatus("fail");
                        response.setMessage("Invalid URL. Supported URL types include http and https.");
                        break;
                    }
                }
            }
            // Request check passed
            if (flag) {
                Archive archive = new Archive();
                archive.setTitle(title);
                archive.setSlug(slug);
                archive.setStatus(status);
                archive.setDescription(description);
                List<File> archiveFileList = new ArrayList<>();
                for (FrontendFile frontendFile : fileList) {
                    File file = new File();
                    file.setName(frontendFile.getFileName());
                    file.setUrl(frontendFile.getFileUrl());
                    archiveFileList.add(file);
                }
                archive.setFileListJsonObj(new Gson().toJson(archiveFileList));
                archiveService.adminSaveArchive(archive);
                response.setStatus("success");
                response.setMessage("Archive was saved successfully.");
            }
        } catch (Exception e) {
            response.setStatus("fail");
            response.setMessage("Internal Server Error.");
        }
        return response;
    }

    @RequestMapping(value = "/check_slug", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ArchiveSlugCheckResponse handleArchiveSlugCheck(@RequestParam("slug") String slug) {
        ArchiveSlugCheckResponse response = new ArchiveSlugCheckResponse();
        Pattern slugPattern = Pattern.compile("^[a-z0-9\\-]+$");
        if (!slugPattern.matcher(slug).matches()) {
            response.setStatus("invalid");
            return response;
        }
        if (0 != this.archiveService.adminCheckArchiveSlug(slug)) {
            response.setStatus("unavailable");
        } else {
            response.setStatus("available");
        }
        return response;
    }

    @RequestMapping(value = "/list_archive", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public PaginatedArchiveListResponse handlePaginatedArchiveList(@RequestParam("page") long page) {
        final long pageSize = 10;
        long archiveNum = this.archiveService.adminGetArchiveCount();
        long pageCount = new BigDecimal(archiveNum).divide(new BigDecimal(pageSize), RoundingMode.UP).longValue();
        // TODO change to frontend archive display
        List<Archive> paginatedArchiveList = this.archiveService.adminGetPaginatedArchive(
                pageSize, Math.min(archiveNum, (page - 1) * pageSize));
        PaginatedArchiveListResponse response = new PaginatedArchiveListResponse();
        response.setPageCount(pageCount);
        return response;
    }
}
