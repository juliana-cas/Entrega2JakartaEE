package mapper.dtos;
public record StudentDto(
        Long studentId,
        String studentName,
        String studentEmail,
        String career,
        String semester) {

}