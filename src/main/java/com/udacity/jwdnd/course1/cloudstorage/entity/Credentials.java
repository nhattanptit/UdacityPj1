package com.udacity.jwdnd.course1.cloudstorage.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {

    private int credentialId;
    private String url;
    private String userName;
    private byte[] key;
    private String passWord;
    private int userId;
}



