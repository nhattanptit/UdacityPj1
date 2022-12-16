package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class FilesService {

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private UserService userService;

    public ArrayList<Files> getAllFile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getPrincipal().toString();
        Integer userId = userService.getUserIdByUsername(username);
        if(userId == 0){
            return new ArrayList<>();
        }
        return filesMapper.getAllFiles(userId);
    }

    public int addFiles(MultipartFile uploadFile, String fileName) throws IOException {
        Files files = new Files();
        files.setFileName(fileName);
        files.setFileSize(uploadFile.getSize()+"");
        files.setContentType(uploadFile.getContentType());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  authentication.getPrincipal().toString();
        files.setUserId(userService.getUserIdByUsername(username));

        files.setFileData(uploadFile.getBytes());
        int count =  filesMapper.createFiles(files);
        return count;

    }

    public boolean deleteFile(int fileId){
        int deletedFile = filesMapper.deleteFiles(fileId);
        if(deletedFile == 1){
            return true;
        }
        return  false;
    }

    public Files getFilesData(int fileId){
        return filesMapper.getFilesData(fileId);
    }

    public boolean isFileExisted(String fileName){
        if(filesMapper.filesNameCount(fileName) > 0) {
            return true;
        }
        return false;
    }
}
