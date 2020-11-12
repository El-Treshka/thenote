package ua.trshk.note.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.trshk.note.component.NoteMapper;
import ua.trshk.note.dto.NoteDTO;
import ua.trshk.note.entity.Note;
import ua.trshk.note.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper mapper;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.mapper = noteMapper;
    }

    @Transactional
    public List<NoteDTO> findByDirectoryId(Integer directoryId) {
        List<NoteDTO> list = new ArrayList<>();
        for (Note note : noteRepository.findByDirectory_Id(directoryId))
            list.add(mapper.toDto(note));
        return list;
    }

    @Transactional
    public boolean existById(Integer id) {
        return noteRepository.existsById(id);
    }

    @Transactional
    public NoteDTO findById(Integer id) {
        return mapper.toDto(noteRepository.findNoteById(id));
    }

    @Transactional
    public void add(NoteDTO note) {
        noteRepository.save(mapper.toEntity(note));
    }

    @Transactional
    public void update(NoteDTO note) {
        noteRepository.save(mapper.toEntity(note));
    }

    @Transactional
    public void deleteById(Integer id) {
        noteRepository.deleteById(id);
    }

}
