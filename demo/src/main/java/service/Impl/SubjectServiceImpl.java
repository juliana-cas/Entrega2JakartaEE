package service.Impl;


import mapper.dtos.SubjectDto;
import repository.Impl.SubjectRepositoryLogicImpl;
import service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepositoryLogicImpl repo;

    public SubjectServiceImpl(SubjectRepositoryLogicImpl repo) {
        this.repo = repo;
    }

    @Override
    public List<SubjectDto> subjectList() {
        return repo.subjectList();
    }

    @Override
    public SubjectDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(SubjectDto subject) {
        repo.update(subject);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}