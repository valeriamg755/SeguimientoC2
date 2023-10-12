package domain.mapping.mappers;

import domain.mapping.dto.TeacherDto;
import domain.models.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {
    public static TeacherDto mapFrom(Teacher source){
        return new TeacherDto(source.getId(),
                source.getName(),
                source.getEmail());
    }
    public static Teacher mapFrom(TeacherDto source){
        return new Teacher(source.id(),
                source.name(),
                source.email());
    }
    public static List<TeacherDto> mapFrom(List<Teacher> source){
        return source.stream().map(TeacherMapper::mapFrom).collect(Collectors.toList());
    }
}
