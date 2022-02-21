package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.dto.LessonDto;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class File {
    private UUID id;
    private String fileName;
    private String fileType;
    private LessonDto lessonDto;
}
