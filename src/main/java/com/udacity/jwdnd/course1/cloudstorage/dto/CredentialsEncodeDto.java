package com.udacity.jwdnd.course1.cloudstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CredentialsEncodeDto {

    private int credentialId;
    private String url;
    private String userName;
    private byte[] key;
    private String passWord;
    private int userId;
    private String encodePassWord;

}
