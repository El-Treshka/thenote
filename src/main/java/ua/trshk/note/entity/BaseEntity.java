package ua.trshk.note.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    Integer id;
}
