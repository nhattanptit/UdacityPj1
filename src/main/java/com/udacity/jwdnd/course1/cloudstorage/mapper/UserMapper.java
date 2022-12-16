package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE userName = #{userName}")
    Users getAllUsers(String user);

    @Insert("INSERT INTO USERS(userName, salt, passWord, firstName, lastName) VALUES (#{userName}, #{salt}, #{passWord}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int createUsers(Users user);

    @Select("SELECT userId FROM USERS WHERE userName = #{userName}")
    Integer getByUserId(String userName);
}
