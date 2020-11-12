package ua.trshk.note.component;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.trshk.note.dto.DirectoryDTO;
import ua.trshk.note.entity.Directory;

import java.util.Objects;

@Component
public class DirectoryMapper implements BaseMapper<Directory, DirectoryDTO> {

    private final ModelMapper mapper;

    public DirectoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Directory toEntity(DirectoryDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Directory.class);
    }

    @Override
    public DirectoryDTO toDto(Directory entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, DirectoryDTO.class);
    }
}
