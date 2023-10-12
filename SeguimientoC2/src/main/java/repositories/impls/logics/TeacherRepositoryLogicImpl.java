package repositories.impls.logics;

import domain.models.Teacher;
import exceptions.SeguimientoException;
import repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<Teacher> {
    private List<Teacher> teachers;
    public TeacherRepositoryLogicImpl() {
        Teacher t1 = new Teacher(1L,"Violet", "1234@cue.edu.co");
        Teacher t2 = new Teacher(2L,"Xaden", "1234@cue.edu.co");
        Teacher t3 = new Teacher(3L,"Liam","1234@cue.edu.co");
        teachers = new ArrayList<>(List.of(t1, t2, t3));
    }
    @Override
    public List<Teacher> list() {
        return teachers;
    }
    @Override
    public Teacher byId(Long id) {
        return teachers.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new SeguimientoException("Teacher not found"));
    }
    @Override
    public void save(Teacher teacher) {
        teachers.add(teacher);
    }
    @Override
    public void delete(Long id) {
        teachers.removeIf(e->e.getId().equals(id));
    }
}
