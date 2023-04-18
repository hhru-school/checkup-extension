package ru.hh.school.checkupextension.core.data.service;

import ru.hh.school.checkupextension.core.data.daoimpl.ProblemDaoImpl;
import ru.hh.school.checkupextension.core.data.daoimpl.SubmissionDaoImpl;
import ru.hh.school.checkupextension.core.data.dto.SubmissionDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Submission;
import ru.hh.school.checkupextension.utils.exception.ResourceNotFoundException;
import ru.hh.school.checkupextension.core.data.request.SubmissionRequestDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.checkupextension.utils.exception.ResourceNotFoundException.PROBLEM_ID_NOT_FOUND_MESSAGE;
import static ru.hh.school.checkupextension.utils.exception.ResourceNotFoundException.SUBMISSION_ID_NOT_FOUND_MESSAGE;

/**
 * SubmissionService - это класс, который содержит бизнес-логику для работы с сущностями типа Submission и Problem.
 * Он реализует логику создания, чтения, обновления и удаления сущностей, а также любую другую бизнес-логику,
 * связанную с этими сущностями.
 */
public class SubmissionService {
  private final SubmissionDaoImpl submissionDao;
  private final ProblemDaoImpl problemDao;

  public SubmissionService(SubmissionDaoImpl submissionDao, ProblemDaoImpl problemDao) {
    this.submissionDao = submissionDao;
    this.problemDao = problemDao;
  }

  public SubmissionDto getById(Long id) {
    Submission submission = submissionDao.getById(id);
    if (submission == null) {
      throw new ResourceNotFoundException(String.format(SUBMISSION_ID_NOT_FOUND_MESSAGE, id));
    }
    return new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
        submission.getSolution(), submission.getStatus());
  }

  public List<SubmissionDto> getAll() {
    List<Submission> submissions = submissionDao.getAll();
    return submissions.stream().map(submission ->
        new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
            submission.getSolution(), submission.getStatus())).collect(Collectors.toList());
  }

  public SubmissionDto create(SubmissionRequestDto requestDto) {
    Problem problem = problemDao.getById(requestDto.getProblemId());
    if (problem == null) {
      throw new ResourceNotFoundException(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId()));
    }
    Submission submission = new Submission(requestDto.getUserId(), problem, requestDto.getSolution(), "PENDING");
    submissionDao.create(submission);
    return new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
        submission.getSolution(), submission.getStatus());
  }

  public SubmissionDto update(Long id, SubmissionRequestDto requestDto) {
    Submission submission = submissionDao.getById(id);
    if (submission == null) {
      throw new ResourceNotFoundException(String.format(SUBMISSION_ID_NOT_FOUND_MESSAGE, id));
    }
    Problem problem = problemDao.getById(requestDto.getProblemId());
    if (problem == null) {
      throw new ResourceNotFoundException(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId()));
    }
    submission.setUser(requestDto.getUserId());
    submission.setProblem(problem);
    submission.setSolution(requestDto.getSolution());
    submissionDao.update(submission);
    return new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
        submission.getSolution(), submission.getStatus());
  }

  public void delete(Long id) {
    submissionDao.delete(id);
  }
}