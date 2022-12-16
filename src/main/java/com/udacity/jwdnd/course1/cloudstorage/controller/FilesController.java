package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FilesService filesService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile
            , @RequestParam("fileName") String fileName, Model model) throws IOException, IOException {
        boolean result = false;
        String errMsg;
        if(multipartFile.isEmpty()){
            errMsg = "Upload file cannot be empty!";
            model.addAttribute("errMsg", errMsg);
        }
        else if(filesService.isFileExisted(fileName)){
            errMsg = "File is existed!";
            model.addAttribute("errMsg", errMsg);
        }
        else {
            int count = filesService.addFiles(multipartFile, fileName);
            result = (count == 1) ? true : false;
        }
        model.addAttribute("result", result);
        return "result";

    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable(name = "fileId", required = true) int fileId){
        filesService.deleteFile(fileId);
        return "redirect:/home";
    }

    @GetMapping("/download/{fileId}")
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(@PathVariable(name = "fileId", required = true) int fileId){
        Files fileData = filesService.getFilesData(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", fileData.getContentType());
        return new ResponseEntity<>(fileData.getFileData(), headers, HttpStatus.OK);
    }
}
