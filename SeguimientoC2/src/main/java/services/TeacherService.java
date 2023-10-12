package services;

import domain.mapping.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> list();
    TeacherDto byId(Long id);
    void save(TeacherDto t);
    void delete(Long id);
}
