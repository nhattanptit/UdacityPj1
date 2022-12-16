package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    ArrayList<Credentials> getAllCredential(int userId);

    @Insert("INSERT INTO CREDENTIALS(url, userName, key, passWord, userId) VALUES (#{url}, #{userName}, #{key}, #{passWord}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credentials credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, userName = #{userName}, key = #{key}, passWord = #{passWord} WHERE credentialId = #{credentialId}")
    int updateCredential(String url, String userName, byte[] key, String passWord, int credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int deleteCredential(int credentialId);
}
