package ua.trshk.note.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "note")
@Data
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false)
    private Integer id;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "directory_id", referencedColumnName = "id", nullable = false)
    private Directory directory;

    @Column(name = "title")
    private String title;

    @Column(name = "text", length = 500, nullable = false)
    private String text;

}
