package ru.hh.school.checkupextension.core.data.service;

import ru.hh.school.checkupextension.core.data.daoimpl.ProblemDaoImpl;
import ru.hh.school.checkupextension.core.data.dto.ProblemDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException;
import ru.hh.school.checkupextension.core.data.request.ProblemRequestDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException.PROBLEM_ID_NOT_FOUND_MESSAGE;

/**
 * ProblemService - это класс, который содержит бизнес-логику для работы с сущностями типа Problem.
 * Он реализует логику создания, чтения, обновления и удаления сущностей, а также любую другую бизнес-логику,
 * связанную с этими сущностями.
 */
public class ProblemService {
  private final ProblemDaoImpl problemDao;

  public ProblemService(ProblemDaoImpl problemRepository) {
    this.problemDao = problemRepository;
  }

  public ProblemDto getById(Long id) {
    Problem problem = problemDao.getById(id);
    if (problem == null) {
      throw new ResourceNotFoundException(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, id));
    }
    return toDto(problem);
  }

  public List<ProblemDto> getAll() {
    List<Problem> problems = problemDao.getAll();
    return problems.stream()
        .map(this::toDto)
        .collect(Collectors.toList());
  }

  public ProblemDto create(ProblemRequestDto requestDto) {
    Problem problem = new Problem();
    problem.setCondition(requestDto.getCondition());
    problem.setType(requestDto.getType());
    problem.setMaxAttempts(requestDto.getMaxAttempts());
    Problem savedProblem = problemDao.create(problem);
    return toDto(savedProblem);
  }

  public ProblemDto update(Long id, ProblemRequestDto requestDto) {
    Problem problem = problemDao.getById(id);
    if (problem == null) {
      throw new ResourceNotFoundException(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, id));
    }
    problem.setCondition(requestDto.getCondition());
    problem.setType(requestDto.getType());
    problem.setMaxAttempts(requestDto.getMaxAttempts());
    Problem savedProblem = problemDao.update(problem);
    return toDto(savedProblem);
  }

  public void delete(Long id) {
    problemDao.delete(id);
  }

  private ProblemDto toDto(Problem problem) {
    return new ProblemDto(
        problem.getId(),
        problem.getCondition(),
        problem.getType(),
        problem.getMaxAttempts()
    );
  }
}