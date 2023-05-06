package ru.hh.school.checkupextension.utils.stub;

import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.repository.Repository;

import java.util.List;
import java.util.Optional;

public class ProblemRepositoryStub implements Repository<ProblemEntity> {
    @Override
    public Optional<ProblemEntity> getById(Long id) {
        var problem = new ProblemEntity();
        problem.setId(1L);
//        problem.setCondition("""
//                {
//                    Statement: "markdown",
//                    Screenshot: "url"
//                }
//                """);
        problem.setType((byte) 1);
        return Optional.of(problem);
    }

    @Override
    public ProblemEntity create(ProblemEntity problem) {
        return null;
    }

    @Override
    public ProblemEntity update(ProblemEntity problem) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ProblemEntity> getAll() {
        return null;
    }
}
