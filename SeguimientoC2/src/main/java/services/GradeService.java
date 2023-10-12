package services;

import domain.mapping.dto.GradeDto;

import java.util.List;

public interface GradeService {
    List<GradeDto> list();
    GradeDto byId(Long id);
    void save(GradeDto student);
    void delete(Long id);
}
