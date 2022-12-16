package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    ArrayList<Files> getAllFiles(int userId);

    @Insert("INSERT INTO FILES(filename, contentType, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    int createFiles(Files file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFiles(int fileId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFilesData(int fileId);

    @Select("SELECT COUNT (filename) FROM FILES WHERE filename = #{fileName}")
    int filesNameCount(String fileName);
}
