package services;

import domain.mapping.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> list();
    StudentDto byId(Long id);
    void save(StudentDto t);
    void delete(Long id);
}
