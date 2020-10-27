package ua.trshk.note.repository;

import org.springframework.data.repository.CrudRepository;
import ua.trshk.note.entity.Note;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    List<Note> findByDirectory_Id(Integer directory_id);

    boolean existsById(Integer id);

    List<Note> findAll();

    Note findNoteById(Integer id);

}