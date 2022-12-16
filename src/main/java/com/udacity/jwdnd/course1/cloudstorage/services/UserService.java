package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Users;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {


    private final UserMapper userMapper;
    private final HashValueEncoderService hashValueEncoderService;

    public UserService(UserMapper userMapper, HashValueEncoderService hashValueEncoderService) {
        this.userMapper = userMapper;
        this.hashValueEncoderService = hashValueEncoderService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getAllUsers(username) == null;
    }

    public int createUser(Users users){
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassWord = hashValueEncoderService.getHashedValue(users.getPassWord(), encodedSalt);
        return userMapper.createUsers(new Users(null, users.getUserName(), encodedSalt, hashPassWord, users.getFirstName(), users.getLastName()));
    }

    public Users getUser(String username){
        return userMapper.getAllUsers(username);
    }

    public int getUserIdByUsername(String userName){
        Integer userIdGot =  userMapper.getByUserId(userName);
        if(userIdGot == null){
            return 0;
        }
        return userIdGot;
    }
}
