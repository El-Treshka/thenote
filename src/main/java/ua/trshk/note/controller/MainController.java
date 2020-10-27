package ua.trshk.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.trshk.note.entity.Directory;
import ua.trshk.note.entity.Note;
import ua.trshk.note.service.DirectoryService;
import ua.trshk.note.service.NoteService;

import java.util.List;

@Controller
public class MainController {
    private final DirectoryService directoryService;
    private final NoteService noteService;

    @Autowired
    public MainController(DirectoryService directoryService, NoteService noteService) {
        this.directoryService = directoryService;
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/directory";
    }

    @GetMapping("/directory")
    public String viewDirectories(Model model) {
        model.addAttribute("directories", directoryService.findAll());
        model.addAttribute("directory", new Directory());
        return "directory";
    }

    @GetMapping("/directory/{id}")
    public String viewNotes(@PathVariable Integer id, Model model) {
        if (!directoryService.existsById(id))
            return "redirect:/directory";
        List<Note> notes = noteService.findByDirectoryId(id);
        model.addAttribute("notes", notes);
        model.addAttribute("note", new Note());
        model.addAttribute("directory", directoryService.findById(id));
        return "notes";
    }

    @GetMapping("/directory/{directoryId}/note/{id}")
    public String viewNote(@PathVariable Integer directoryId,
                           @PathVariable Integer id, Model model) {
        if (!noteService.existById(id))
            return "redirect:/directory/" + directoryId;
        Note note = noteService.findById(id);
        model.addAttribute("note", note);
        model.addAttribute("directory", note.getDirectory());
        return "note";

    }

    @PostMapping("/directory/add")
    public String createDirectory(@ModelAttribute Directory directory, Model model) {
        String status = "not added";
        if (directory != null && directory.getName() != null) {
            directoryService.add(directory);
            status = "added";
        }
        model.addAttribute("status", status);
        return "redirect:/directory";
    }

    @PostMapping("/directory/{id}/delete")
    public String deleteDirectory(@PathVariable Integer id, Model model) {
        String status = "not deleted";
        Directory directory = directoryService.findById(id);
        if (directory != null) {
            status = "deleted";
            directoryService.delete(directory);
        }
        model.addAttribute("status", status);
        return "redirect:/directory";
    }

    @PostMapping("/directory/{id}/update")
    public String updateDirectory(@PathVariable Integer id,
                                  @ModelAttribute Directory directory, Model model) {
        String status = "not updated";
        if (directory != null && directory.getName() != null &&
                directory.getId() != null && directoryService.existsById(directory.getId()) &&
                directory.getId().equals(id)) {
            status = "updated";
            directoryService.update(directory);
        }
        model.addAttribute("status", status);
        return "redirect:/directory/" + id;
    }

    @PostMapping("/directory/{id}/note/add")
    public String createNote(@PathVariable Integer id,
                             @ModelAttribute Note note, Model model) {
        String status = "not added";
        if (note != null && note.getText() != null) {
            note.setDirectory(directoryService.findById(id));
            noteService.add(note);
            status = "added";
        }
        model.addAttribute("status", status);
        return "redirect:/directory/" + id;
    }

    @PostMapping("/directory/{directoryId}/note/{id}/delete")
    public String deleteNote(@PathVariable Integer id, Model model,
                             @PathVariable Integer directoryId) {
        if (noteService.existById(id)) {
            noteService.deleteById(id);
            model.addAttribute("status", "deleted");
            return "redirect:/directory/" + directoryId;
        }
        model.addAttribute("status", "not deleted");
        return String.format("redirect:/directory/%s/note/%s", directoryId, id);
    }

    @PostMapping("/directory/{directoryId}/note/{id}/update")
    public String updateNote(@PathVariable Integer id,
                             @ModelAttribute Note note,
                             Model model, @PathVariable Integer directoryId) {
        String status = "not updated";
        if (note != null && note.getText() != null && note.getId() != null &&
                note.getId().equals(id) && noteService.existById(id) &&
                directoryService.existsById(directoryId)) {
            note.setDirectory(directoryService.findById(directoryId));
            noteService.update(note);
            status = "updated";
        }
        model.addAttribute("status", status);
        return String.format("redirect:/directory/%s/note/%s", directoryId, id);
    }
}
