package domain.mapping.mappers;

import domain.mapping.dto.SubjectDto;
import domain.models.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {
    public static SubjectDto mapFrom(Subject source){
        return new SubjectDto(source.getId(),
                source.getName(),
                source.getTeacher());
    }
    public static Subject mapFrom(SubjectDto source){
        return new Subject(source.id(),
                source.name(),
                source.teacher());
    }
    public static List<SubjectDto> mapFrom(List<Subject> source){
        return source.stream().map(SubjectMapper::mapFrom).collect(Collectors.toList());
    }
}
