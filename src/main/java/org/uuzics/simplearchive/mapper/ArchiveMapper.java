package org.uuzics.simplearchive.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.uuzics.simplearchive.entity.Archive;

@Mapper
@Repository
public interface ArchiveMapper {
    @Select("""
            SELECT id, slug, title, description, status
            FROM archive
            WHERE slug=#{slug} AND status="active"
            """)
    @Results({
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "slug", column = "slug", javaType = String.class),
            @Result(property = "title", column = "title", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class)
    })
    Archive getArchiveBySlug(String slug);
}
