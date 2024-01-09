package org.uuzics.simplearchive.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.uuzics.simplearchive.entity.Archive;
import org.uuzics.simplearchive.entity.FrontendListedArchive;

import java.util.List;

@Mapper
@Repository
public interface ArchiveMapper {
    @Select("""
            SELECT id, slug, title, description, file_list, status
            FROM archive
            WHERE slug=#{slug} AND status="active"
            """)
    @Results({
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "slug", column = "slug", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "fileListJsonObj", column = "file_list", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class)
    })
    Archive getArchiveBySlug(String slug);

    @Select("""
            SELECT id, slug, title, description, file_list, status
            FROM archive
            WHERE slug=#{slug}
            """)
    @Results({
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "slug", column = "slug", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "fileListJsonObj", column = "file_list", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class)
    })
    Archive adminGetArchiveBySlug(String slug);

    @Select("""
            SELECT id, slug, title, description, file_list, status
            FROM archive
            WHERE id=#{id}
            """)
    @Results({
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "slug", column = "slug", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "fileListJsonObj", column = "file_list", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class)
    })
    Archive adminGetArchiveById(long id);

    @Select("""
            SELECT id, slug, title
            FROM archive
            ORDER BY id
            LIMIT #{limit} OFFSET #{offset};
            """)
    @Results({
            @Result(property = "archiveId", column = "id", javaType = Long.class),
            @Result(property = "archiveSlug", column = "slug", javaType = String.class),
            @Result(property = "archiveName", column = "title", javaType = String.class)
    })
    List<FrontendListedArchive> adminGetPaginatedArchive(long limit, long offset);

    @Select("""
            SELECT count(*)
            FROM ARCHIVE
            """)
    long adminGetArchiveCount();

    @Insert("""
            INSERT INTO archive(id, slug, title, description, file_list, status)
            VALUES(#{id}, #{slug}, #{title}, #{description}, #{fileListJsonObj}, #{status})
            ON CONFLICT(id) DO UPDATE SET
            slug = excluded.slug,
            title = excluded.title,
            description = excluded.description,
            file_list = excluded.file_list,
            status = excluded.status;
            """)
    void adminEditArchive(Archive archive);

    @Insert("""
            INSERT INTO archive(slug, title, description, file_list, status)
            VALUES(#{slug}, #{title}, #{description}, #{fileListJsonObj}, #{status})
            """)
    void adminNewArchive(Archive archive);

    @Select("""
            SELECT count(*)
            FROM archive
            WHERE slug=#{slug}
            """)
    long adminCheckArchiveSlug(String slug);
}
