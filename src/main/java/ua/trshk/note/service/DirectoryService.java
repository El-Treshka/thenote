package ua.trshk.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.trshk.note.entity.Directory;
import ua.trshk.note.repository.DirectoryRepository;

import java.util.List;

@Service
public class DirectoryService {

    private final DirectoryRepository directoryRepository;

    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    @Transactional
    public void deleteById(Integer id){
        directoryRepository.deleteById(id);
    }

    @Transactional
    public Directory findById(Integer id) {
        return directoryRepository.findDirectoryById(id);
    }

    @Transactional
    public boolean existsById(Integer id) {
        return directoryRepository.existsById(id);
    }

    @Transactional
    public List<Directory> findAll() {
        return directoryRepository.findAll();
    }

    @Transactional
    public Directory add(Directory directory) {
        return directoryRepository.save(directory);
    }

    @Transactional
    public void update(Directory directory) {
        directoryRepository.save(directory);
    }

    @Transactional
    public void delete(Directory directory) {
        directoryRepository.delete(directory);
    }

}
