package ua.trshk.note.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "note")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note extends BaseEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "directory_id", referencedColumnName = "id", nullable = false)
    private Directory directory;

    @Column(name = "title")
    private String title;

    @Column(name = "text", length = 500, nullable = false)
    private String text;

}
