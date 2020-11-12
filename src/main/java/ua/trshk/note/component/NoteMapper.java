package ua.trshk.note.component;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.trshk.note.dto.NoteDTO;
import ua.trshk.note.entity.Note;

import java.util.Objects;

@Component
public class NoteMapper implements BaseMapper<Note, NoteDTO>{

    private final ModelMapper mapper;

    public NoteMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Note toEntity(NoteDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Note.class);
    }

    @Override
    public NoteDTO toDto(Note entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, NoteDTO.class);
    }
}
