package services.impl;

import domain.mapping.dto.SubjectDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repositories.Repository;
import repositories.impls.normal.SubjectRepositoryImpl;
import services.SubjectService;

import java.sql.Connection;
import java.util.List;

@ApplicationScoped
public class SubjectServiceImpl implements SubjectService {
    @Inject
    @Named("SubjectService")
    private Repository<SubjectDto> repo;

    /*public SubjectServiceImpl(Connection connection){
        this.repo = new SubjectRepositoryImpl(connection);
    }*/

    @Override
    public List<SubjectDto> list() {
        return repo.list();
    }

    @Override
    public SubjectDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void save(SubjectDto subject) {
        repo.save(subject);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
