package repositories.impls.logics;

import domain.models.Subject;
import domain.models.Teacher;
import exceptions.SeguimientoException;
import repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<Subject> {
    private List<Subject> subjects;
    public SubjectRepositoryLogicImpl() {
        Subject su1 = new Subject(1L,"History", new Teacher());
        Subject su2 = new Subject(2L,"Physics", new Teacher());
        Subject su3 = new Subject(3L,"Chemistry", new Teacher());
        subjects = new ArrayList<>(List.of(su1, su2, su3));
    }
    @Override
    public List<Subject> list() {
        return subjects;
    }
    @Override
    public Subject byId(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new SeguimientoException("Subject not found"));
    }
    @Override
    public void save(Subject subject) {
        subjects.add(subject);
    }
    @Override
    public void delete(Long id) {
        subjects.removeIf(e->e.getId().equals(id));
    }
}
