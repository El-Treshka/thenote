package ua.trshk.note.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDTO implements Serializable {
    private Integer id;
}
