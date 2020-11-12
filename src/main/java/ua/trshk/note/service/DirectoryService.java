package ua.trshk.note.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.trshk.note.component.DirectoryMapper;
import ua.trshk.note.dto.DirectoryDTO;
import ua.trshk.note.entity.Directory;
import ua.trshk.note.repository.DirectoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectoryService {

    private final DirectoryRepository directoryRepository;
    private final DirectoryMapper mapper;

    public DirectoryService(DirectoryRepository directoryRepository, DirectoryMapper directoryMapper) {
        this.directoryRepository = directoryRepository;
        this.mapper = directoryMapper;
    }

    @Transactional
    public void deleteById(Integer id) {
        directoryRepository.deleteById(id);
    }

    @Transactional
    public DirectoryDTO findById(Integer id) {
        return mapper.toDto(directoryRepository.findDirectoryById(id));
    }

    @Transactional
    public boolean existsById(Integer id) {
        return directoryRepository.existsById(id);
    }

    @Transactional
    public List<DirectoryDTO> findAll() {
        List<DirectoryDTO> list = new ArrayList<>();
        for (Directory directory : directoryRepository.findAll())
            list.add(mapper.toDto(directory));
        return list;
    }

    @Transactional
    public DirectoryDTO add(DirectoryDTO directory) {
        return mapper.toDto(directoryRepository.save(mapper.toEntity(directory)));
    }

    @Transactional
    public void update(DirectoryDTO directory) {
        directoryRepository.save(mapper.toEntity(directory));
    }

    @Transactional
    public void delete(DirectoryDTO directory) {
        directoryRepository.delete(mapper.toEntity(directory));
    }

}
