package services.impl;

import domain.mapping.dto.StudentDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.Repository;
import services.StudentService;

import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {
    @Inject
    @Named("StudentService")
    private Repository<StudentDto> repo;
    /*public StudentServiceImpl(Connection connection) {
        this.repo = new StudentRepositoryJDBCImpl(connection);
    }*/

    @Override
    public List<StudentDto> list() {
        return repo.list();
    }

    @Override
    public StudentDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(StudentDto student) {
        repo.save(student);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
