package org.uuzics.simplearchive.service;

import org.uuzics.simplearchive.entity.Archive;

public interface ArchiveService {
    Archive getArchiveBySlug(String slug);
}
