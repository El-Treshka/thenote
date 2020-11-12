package ua.trshk.note.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.trshk.note.dto.DirectoryDTO;
import ua.trshk.note.dto.NoteDTO;
import ua.trshk.note.service.DirectoryService;
import ua.trshk.note.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIMainController {

    private final NoteService noteService;
    private final DirectoryService directoryService;

    public APIMainController(NoteService noteService, DirectoryService directoryService) {
        this.noteService = noteService;
        this.directoryService = directoryService;
    }

    @GetMapping("/directories")
    public ResponseEntity<?> viewDirectories() {
        List<DirectoryDTO> directories = directoryService.findAll();
        if (directories.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(directories);
    }

    @PostMapping("/directories")
    public ResponseEntity<?> createDirectory(@RequestBody DirectoryDTO directory) {
        if (directory != null && directory.getName() != null) {
            DirectoryDTO addedDirectory = directoryService.add(directory);
            return ResponseEntity.ok(addedDirectory);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/directories/{directoryId}")
    public ResponseEntity<?> updateDirectory(@PathVariable Integer directoryId,
                                             @RequestBody DirectoryDTO directory) {
        if (directoryService.existsById(directoryId)
                && directory != null
                && directory.getName() != null) {
            directory.setId(directoryId);
            directoryService.update(directory);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/directories/{directoryId}")
    public ResponseEntity<?> deleteDirectory(@PathVariable Integer directoryId) {
        if (directoryService.existsById(directoryId)) {
            directoryService.deleteById(directoryId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/directories/{directoryId}/notes")
    public ResponseEntity<?> viewNotes(@PathVariable Integer directoryId) {
        if (directoryService.existsById(directoryId)) {
            List<NoteDTO> notes = noteService.findByDirectoryId(directoryId);
            if (notes.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(notes);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/directories/{directoryId}/notes")
    public ResponseEntity<?> createNote(@PathVariable Integer directoryId,
                                        @RequestBody NoteDTO note) {
        if (directoryService.existsById(directoryId)
                && note != null
                && note.getText() != null) {
            note.setDirectory(directoryService.findById(directoryId));
            noteService.add(note);
            return ResponseEntity.ok(note);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/directories/{directoryId}/notes/{noteId}")
    public ResponseEntity<?> updateNote(@PathVariable Integer directoryId,
                                        @PathVariable Integer noteId,
                                        @RequestBody NoteDTO note) {
        if (directoryService.existsById(directoryId)
                && noteService.existById(noteId)
                && note != null
                && note.getText() != null) {
            note.setDirectory(directoryService.findById(directoryId));
            note.setId(noteId);
            noteService.update(note);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/directories/{directoryId}/notes/{noteId}")
    public ResponseEntity<?> deleteNote(@PathVariable Integer directoryId,
                                        @PathVariable Integer noteId) {
        if (directoryService.existsById(directoryId)
                && noteService.existById(noteId)) {
            noteService.deleteById(noteId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
