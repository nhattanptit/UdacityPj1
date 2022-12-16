package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Files;
import com.udacity.jwdnd.course1.cloudstorage.entity.Notes;
import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialsEncodeDto;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private FilesService filesService;

    @Autowired
    private NotesService noteService;
    
    @Autowired
    private CredentialsService credentialsService;

    @GetMapping()
    public String showHome(Model model){
        List<Notes> lstUserNote = noteService.getAllNotes();
        List<CredentialsEncodeDto> lstUserCredential = credentialsService.getAllCredential();
        List<Files> lstUserFile = filesService.getAllFile();

        model.addAttribute("lstNote", lstUserNote);
        model.addAttribute("lstCredential", lstUserCredential);
        model.addAttribute("lstFile", lstUserFile);

        return "home";
    }
    @GetMapping(value = "/invalid")
    public String invalid(){
        return "error";
    }

}