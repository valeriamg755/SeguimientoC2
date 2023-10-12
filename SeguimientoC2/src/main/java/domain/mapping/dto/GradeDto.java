package domain.mapping.dto;

import domain.models.Student;
import domain.models.Subject;

public record GradeDto(Long id,
                       Student student,
                       Subject subject,
                       Double grade) {
}
