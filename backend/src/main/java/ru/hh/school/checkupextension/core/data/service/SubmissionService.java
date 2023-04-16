package ru.hh.school.checkupextension.core.data.service;

import org.springframework.stereotype.Service;
import ru.hh.school.checkupextension.core.data.dto.SubmissionDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Submission;
import ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException;
import ru.hh.school.checkupextension.core.data.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.data.repository.SubmissionRepository;
import ru.hh.school.checkupextension.core.data.request.SubmissionRequestDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException.PROBLEM_ID_NOT_FOUND_MESSAGE;
import static ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException.SUBMISSION_ID_NOT_FOUND_MESSAGE;

/**
 * SubmissionService - это класс, который содержит бизнес-логику для работы с сущностями типа Submission и Problem.
 * Он реализует логику создания, чтения, обновления и удаления сущностей, а также любую другую бизнес-логику,
 * связанную с этими сущностями.
 */
@Service
public class SubmissionService {
  private final SubmissionRepository submissionRepository;
  private final ProblemRepository problemRepository;

  public SubmissionService(SubmissionRepository submissionRepository, ProblemRepository problemRepository) {
    this.submissionRepository = submissionRepository;
    this.problemRepository = problemRepository;
  }

  public SubmissionDto getById(Long id) {
    Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(SUBMISSION_ID_NOT_FOUND_MESSAGE, id)));
    return new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
        submission.getSolution(), submission.getStatus());
  }

  public List<SubmissionDto> getAll() {
    List<Submission> submissions = submissionRepository.findAll();
    return submissions.stream().map(submission ->
        new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
            submission.getSolution(), submission.getStatus())).collect(Collectors.toList());
  }

  public SubmissionDto create(SubmissionRequestDto requestDto) {
    Problem problem = problemRepository.findById(requestDto.getProblemId())
        .orElseThrow(() -> new ResourceNotFoundException
            (String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId())));
    Submission submission = new Submission(requestDto.getUserId(), problem, requestDto.getSolution(), "PENDING");
    submissionRepository.save(submission);
    return new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
        submission.getSolution(), submission.getStatus());
  }

  public SubmissionDto update(Long id, SubmissionRequestDto requestDto) {
    Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(SUBMISSION_ID_NOT_FOUND_MESSAGE, id)));
    Problem problem = problemRepository.findById(requestDto.getProblemId())
        .orElseThrow(() -> new ResourceNotFoundException
            (String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId())));
    submission.setUser(requestDto.getUserId());
    submission.setProblem(problem);
    submission.setSolution(requestDto.getSolution());
    submissionRepository.save(submission);
    return new SubmissionDto(submission.getId(), submission.getUser(), submission.getProblem().getId(),
        submission.getSolution(), submission.getStatus());
  }

  public void delete(Long id) {
    submissionRepository.deleteById(id);
  }
}