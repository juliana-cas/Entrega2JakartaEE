package service.Impl;

import mapper.dtos.StudentDto;
import repository.Impl.StudentRepositoryLogicImpl;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryLogicImpl repo;

    public StudentServiceImpl(StudentRepositoryLogicImpl repo) {
        this.repo = repo;
    }

    @Override
    public List<StudentDto> studentList() {
        return repo.studentList();
    }

    @Override
    public StudentDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(StudentDto student) {
        repo.update(student);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}