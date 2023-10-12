package domain.mapping.mappers;

import domain.mapping.dto.GradeDto;
import domain.models.Grade;

import java.util.List;
import java.util.stream.Collectors;

public class GradeMapper {
    public static GradeDto mapFrom(Grade source){
        return new GradeDto(source.getId(),
                source.getStudent(),
                source.getSubject(),
                source.getGrade());
    }
    public static Grade mapFrom(GradeDto source){
        return new Grade(source.id(),
                source.student(),
                source.subject(),
                source.grade());

    }
    public static List<GradeDto> mapFrom(List<Grade> source){
        return source.stream().map(GradeMapper::mapFrom).collect(Collectors.toList());
    }
}
