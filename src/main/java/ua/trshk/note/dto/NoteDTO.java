package ua.trshk.note.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.trshk.note.entity.Directory;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO extends BaseDTO {
    private DirectoryDTO directory;
    private String title;
    private String text;
}
