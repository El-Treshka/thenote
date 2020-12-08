package ua.trshk.note.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.trshk.note.dto.DirectoryDTO;
import ua.trshk.note.dto.NoteDTO;
import ua.trshk.note.service.DirectoryService;
import ua.trshk.note.service.NoteService;

import java.util.List;

@Controller
@RequestMapping("/web")
public class MainController {
    private final DirectoryService directoryService;
    private final NoteService noteService;

    public MainController(DirectoryService directoryService, NoteService noteService) {
        this.directoryService = directoryService;
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/web/directory";
    }

    @GetMapping("/directory")
    public String viewDirectories(Model model) {
        model.addAttribute("directories", directoryService.findAll());
        model.addAttribute("directory", new DirectoryDTO());
        return "directory";
    }

    @GetMapping("/directory/{id}")
    public String viewNotes(@PathVariable Integer id, Model model) {
        if (!directoryService.existsById(id))
            return "redirect:/web/directory";
        List<NoteDTO> notes = noteService.findByDirectoryId(id);
        model.addAttribute("notes", notes);
        model.addAttribute("note", new NoteDTO());
        model.addAttribute("directory", directoryService.findById(id));
        return "notes";
    }

    @GetMapping("/directory/{directoryId}/note/{id}")
    public String viewNote(@PathVariable Integer directoryId,
                           @PathVariable Integer id, Model model) {
        if (!noteService.existById(id))
            return "redirect:/web/directory/" + directoryId;
        NoteDTO note = noteService.findById(id);
        model.addAttribute("note", note);
        return "note";

    }

    @PostMapping("/directory/add")
    public String createDirectory(@ModelAttribute DirectoryDTO directory) {
        if (directory != null && directory.getName() != null) {
            directoryService.add(directory);
        }
        return "redirect:/web/directory";
    }

    @PostMapping("/directory/{id}/delete")
    public String deleteDirectory(@PathVariable Integer id) {
        DirectoryDTO directory = directoryService.findById(id);
        if (directory != null) {
            directoryService.delete(directory);
        }
        return "redirect:/web/directory";
    }

    @PostMapping("/directory/{id}/update")
    public String updateDirectory(@PathVariable Integer id,
                                  @ModelAttribute DirectoryDTO directory) {
        if (directory != null && directory.getName() != null &&
                directory.getId() != null && directoryService.existsById(directory.getId()) &&
                directory.getId().equals(id)) {
            directoryService.update(directory);
        }
        return "redirect:/web/directory/" + id;
    }

    @PostMapping("/directory/{id}/note/add")
    public String createNote(@PathVariable Integer id,
                             @ModelAttribute NoteDTO note) {
        if (note != null && note.getText() != null) {
            note.setDirectory(directoryService.findById(id));
            noteService.add(note);
        }
        return "redirect:/web/directory/" + id;
    }

    @PostMapping("/directory/{directoryId}/note/{id}/delete")
    public String deleteNote(@PathVariable Integer id,
                             @PathVariable Integer directoryId) {
        if (noteService.existById(id)) {
            noteService.deleteById(id);
            return "redirect:/web/directory/" + directoryId;
        }
        return String.format("redirect:/web/directory/%s/note/%s", directoryId, id);
    }

    @PostMapping("/directory/{directoryId}/note/{id}/update")
    public String updateNote(@PathVariable Integer id,
                             @ModelAttribute NoteDTO note,
                             @PathVariable Integer directoryId) {
        if (note != null && note.getText() != null && note.getId() != null &&
                note.getId().equals(id) && noteService.existById(id) &&
                directoryService.existsById(directoryId)) {
            note.setDirectory(directoryService.findById(directoryId));
            noteService.update(note);
        }
        return String.format("redirect:/web/directory/%s/note/%s", directoryId, id);
    }
}
