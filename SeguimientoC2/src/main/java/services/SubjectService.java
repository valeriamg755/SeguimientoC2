package services;

import domain.mapping.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> list();
    SubjectDto byId(Long id);
    void save(SubjectDto t);
    void delete(Long id);
}
