package ua.trshk.note.component;

import ua.trshk.note.dto.BaseDTO;
import ua.trshk.note.entity.BaseEntity;


public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {

    E toEntity(D dto);

    D toDto(E entity);

}
