package service.Impl;

import mapper.dtos.TeacherDto;
import repository.Impl.TeacherRepositoryLogicImpl;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryLogicImpl repo;

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repo){
        this.repo = repo;
    }
    @Override
    public List<TeacherDto> teacherList() {
        return repo.teacherList();
    }

    @Override
    public TeacherDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(TeacherDto teacher) {
        repo.update(teacher);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}