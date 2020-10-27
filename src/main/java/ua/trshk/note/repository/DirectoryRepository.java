package ua.trshk.note.repository;

import org.springframework.data.repository.CrudRepository;
import ua.trshk.note.entity.Directory;

import java.util.List;

public interface DirectoryRepository extends CrudRepository<Directory, Integer> {
    List<Directory> findAll();

    boolean existsById(Integer id);

    Directory findDirectoryById(Integer id);

}