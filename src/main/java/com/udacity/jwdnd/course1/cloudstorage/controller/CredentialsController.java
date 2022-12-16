package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialsDto;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(CredentialsDto credentialsDto, Model model) {
        credentialsService.createOrUpdateCrentials(credentialsDto);
        model.addAttribute("result", true);
        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCr(@PathVariable(name = "credentialId", required = true) int credentialId, Model model) {
        credentialsService.deleteCredential(credentialId);
        model.addAttribute("result", true);
        return "result";
    }
}
