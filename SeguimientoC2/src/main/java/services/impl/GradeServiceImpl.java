package services.impl;

import domain.mapping.dto.GradeDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.Repository;
import repositories.impls.normal.GradeRepositoryImpl;
import services.GradeService;

import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class GradeServiceImpl implements GradeService {
    @Inject
    @Named("gradeService")
    private Repository<GradeDto> repo;
    /*public GradeServiceImpl(Connection connection) {
        this.repo = new GradeRepositoryImpl(connection);
    }*/

    @Override
    public List<GradeDto> list() {
        return repo.list();
    }

    @Override
    public GradeDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(GradeDto grades) {
        repo.save(grades);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
