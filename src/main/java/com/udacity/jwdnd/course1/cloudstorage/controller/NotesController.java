package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.NotesDto;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private NotesService noteService;

    public NotesController(NotesService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdateNotes(NotesDto noteForm, Model model){
        noteService.createOrUpdateNotes(noteForm);
        model.addAttribute("result", true);
        return "result";
    }

    @GetMapping("/deleteNotes/{noteId}")
    public String deleteNotes(@PathVariable(name = "noteId", required = true) int noteId, Model model){
        noteService.deleteNotes(noteId);
        model.addAttribute("result", true);
        return "result";
    }
}
