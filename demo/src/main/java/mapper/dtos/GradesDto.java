package mapper.dtos;

import domain.models.Student;
import domain.models.Subject;
import lombok.Builder;

@Builder
public record GradesDto(
        Long gradesId,
        Student student,
        Subject subject,
        double grade) {
}