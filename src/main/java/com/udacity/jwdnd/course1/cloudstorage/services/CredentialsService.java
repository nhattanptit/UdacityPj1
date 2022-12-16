package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialsDto;
import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialsEncodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsMapper credentialsMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptionService encryptionService;

    public ArrayList<CredentialsEncodeDto> getAllCredential(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getPrincipal().toString();
        Integer userId = userService.getUserIdByUsername(username);
        if(userId == 0){
            return new ArrayList<>();
        }
        ArrayList<Credentials> lstCredentials = credentialsMapper.getAllCredential(userId);
        ArrayList<CredentialsEncodeDto> returnList = new ArrayList<>();
        lstCredentials.stream().forEach(credentials -> {
            byte[] key = credentials.getKey();
            String encodedKey = Base64.getEncoder().encodeToString(key);
            String decryptedPassword = encryptionService.decryptValue(credentials.getPassWord(), encodedKey);
            returnList.add(new CredentialsEncodeDto(credentials.getCredentialId(),credentials.getUrl(), credentials.getUserName(),
                    credentials.getKey(),credentials.getPassWord(),credentials.getUserId(), decryptedPassword));
        });
        return returnList;
    }

    public int createOrUpdateCrentials(CredentialsDto credentialsDto){
        Credentials credentials = new Credentials();
        credentials.setUserName(credentialsDto.getUserName());
        credentials.setUrl(credentialsDto.getUrl());
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credentialsDto.getPassWord(), encodedKey);
        credentials.setKey(key);
        credentials.setPassWord(encryptedPassword);

        if(credentialsDto.getCredentialId() == 0){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName =  authentication.getPrincipal().toString();
            credentials.setUserId(userService.getUserIdByUsername(userName));
            return credentialsMapper.createCredential(credentials);
        }

        else{
            credentials.setCredentialId(credentialsDto.getCredentialId());
            return credentialsMapper.updateCredential(credentials.getUrl(), credentials.getUserName(), credentials.getKey(), credentials.getPassWord(), credentials.getCredentialId());
        }

    }

    public boolean deleteCredential(int credentialId){
        int deletedCredential = credentialsMapper.deleteCredential(credentialId);
        if(deletedCredential == 1){
            return true;
        }
        return false;
    }
}
