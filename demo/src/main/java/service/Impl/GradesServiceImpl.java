package service.Impl;


import mapper.dtos.GradesDto;
import repository.GradesRepository;
import repository.Impl.GradesRepositoryImpl;
import service.GradesService;

import java.util.List;

public class GradesServiceImpl implements GradesService {

    GradesRepository repo = new GradesRepositoryImpl();
    @Override
    public List<GradesDto> gradesList() {
        return repo.gradesList();
    }

    @Override
    public GradesDto byId(Long id) {
        return repo.byId(id);
    }

    @Override
    public void update(GradesDto grades) {
        repo.update(grades);
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
