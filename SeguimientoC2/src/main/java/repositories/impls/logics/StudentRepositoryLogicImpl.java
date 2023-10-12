package repositories.impls.logics;

import domain.mapping.dto.StudentDto;
import domain.mapping.mappers.StudentMapper;
import domain.models.Student;
import exceptions.SeguimientoException;
import repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<StudentDto> {
    private List<Student> students;

    public StudentRepositoryLogicImpl() {
        Student s1 = new Student(1L,"Kim Dokja", "kd@cue.edu.com", "III");
        Student s2 = new Student(2L,"Yoo Joonghyuk", "yjh@cue.edu.com", "II");
        Student s3 = new Student(3L,"Han Sooyoung", "hsy@cue.edu.c", "I");
        students = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<StudentDto> list() {
        return StudentMapper.mapFrom(students);
    }

    @Override
    public StudentDto byId(Long id) {
        return list().stream()
                .filter(student -> student.id().equals(id))
                .findFirst()
                .orElseThrow(()-> new SeguimientoException("Student not found"));
    }

    @Override
    public void save(StudentDto student) {
        list().add(student);
    }

    @Override
    public void delete(Long id) {
        list().remove(id);
    }
}
