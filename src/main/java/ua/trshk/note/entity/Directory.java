package ua.trshk.note.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "directory")
@Data
public class Directory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

}
