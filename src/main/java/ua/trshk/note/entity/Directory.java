package ua.trshk.note.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "directory")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Directory extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
