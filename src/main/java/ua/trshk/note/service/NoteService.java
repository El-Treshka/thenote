package ua.trshk.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.trshk.note.entity.Note;
import ua.trshk.note.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    public List<Note> findByDirectoryId(Integer directoryId) {
        return noteRepository.findByDirectory_Id(directoryId);
    }

    @Transactional
    public boolean existById(Integer id) {
        return noteRepository.existsById(id);
    }

    @Transactional
    public Note findById(Integer id) {
        return noteRepository.findNoteById(id);
    }

    @Transactional
    public void add(Note note) {
        noteRepository.save(note);
    }

    @Transactional
    public void update(Note note) {
        noteRepository.save(note);
    }

    @Transactional
    public void deleteById(Integer id) {
        noteRepository.deleteById(id);
    }

}
