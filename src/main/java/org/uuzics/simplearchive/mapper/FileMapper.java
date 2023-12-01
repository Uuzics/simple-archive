package org.uuzics.simplearchive.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.uuzics.simplearchive.entity.File;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {
    @Select("""
            SELECT id, archive_id, slug, name, status
            FROM file
            WHERE archive_id=#{archiveId} AND status="active"
            ORDER BY name ASC
            """)
    @Results({
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "archiveId", column = "archive_id", javaType = Long.class),
            @Result(property = "slug", column = "slug", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class)
    })
    List<File> getFileListByArchiveId(long archiveId);
}
