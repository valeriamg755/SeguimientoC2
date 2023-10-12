package domain.mapping.mappers;

import domain.mapping.dto.StudentDto;
import domain.models.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentDto mapFrom(Student source){
        return new StudentDto(source.getId(),
                source.getName(),
                source.getEmail(),
                source.getSemester());
    }
    //test commit
    public static Student mapFrom(StudentDto source){
        return new Student(source.id(),
                source.name(),
                source.email(),
                source.semester());
    }
    public static List<StudentDto> mapFrom(List<Student> source){
        return source.stream().map(StudentMapper::mapFrom).collect(Collectors.toList());
    }
    public static List<Student> mapFromDto(List<StudentDto> source){
        return source.stream().map(StudentMapper::mapFrom).collect(Collectors.toList());
    }
}
