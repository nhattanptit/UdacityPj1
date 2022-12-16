package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Users {

    private Integer userId;
    private String userName;
    private String salt;
    private String passWord;
    private String firstName;
    private String lastName;
}

