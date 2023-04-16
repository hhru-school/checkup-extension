package ru.hh.school.checkupextension.core.data.service;

import org.springframework.stereotype.Service;
import ru.hh.school.checkupextension.core.data.dto.VerificationDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Verification;
import ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException;
import ru.hh.school.checkupextension.core.data.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.data.repository.VerificationRepository;
import ru.hh.school.checkupextension.core.data.request.VerificationRequestDto;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException.PROBLEM_ID_NOT_FOUND_MESSAGE;
import static ru.hh.school.checkupextension.core.data.exception.ResourceNotFoundException.VERIFICATION_ID_NOT_FOUND_MESSAGE;

/**
 * VerificationService - это класс, который содержит бизнес-логику для работы с сущностями типа Verification и Problem.
 * Он реализует логику создания, чтения, обновления и удаления сущностей, а также любую другую бизнес-логику,
 * связанную с этими сущностями.
 */
@Service
public class VerificationService {
  private final VerificationRepository verificationRepository;
  private final ProblemRepository problemRepository;

  public VerificationService(VerificationRepository verificationRepository, ProblemRepository problemRepository) {
    this.verificationRepository = verificationRepository;
    this.problemRepository = problemRepository;
  }

  public VerificationDto getById(Long id) {
    Verification verification = verificationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id)));
    return mapToDto(verification);
  }

  public List<VerificationDto> getAll() {
    List<Verification> verifications = verificationRepository.findAll();
    return verifications.stream()
        .map(this::mapToDto)
        .collect(Collectors.toList());
  }

  public VerificationDto create(VerificationRequestDto requestDto) {
    Problem problem = problemRepository.findById(requestDto.getProblemId())
        .orElseThrow(() -> new ResourceNotFoundException
            (String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId())));
    Verification verification = mapToEntity(requestDto, problem);
    verificationRepository.save(verification);
    return mapToDto(verification);
  }

  public VerificationDto update(Long id, VerificationRequestDto requestDto) {
    Verification verification = verificationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id)));
    Problem problem = problemRepository.findById(requestDto.getProblemId())
        .orElseThrow(() -> new ResourceNotFoundException
            (String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId())));
    verification.setProblem(problem);
    verification.setContent(requestDto.getContent());
    verificationRepository.save(verification);
    return mapToDto(verification);
  }

  public void delete(Long id) {
    Verification verification = verificationRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id)));
    verificationRepository.delete(verification);
  }

  private Verification mapToEntity(VerificationRequestDto requestDto, Problem problem) {
    Verification verification = new Verification();
    verification.setProblem(problem);
    verification.setContent(requestDto.getContent());
    return verification;
  }

  private VerificationDto mapToDto(Verification verification) {
    VerificationDto dto = new VerificationDto();
    dto.setId(verification.getId());
    dto.setProblemId(verification.getProblem().getId());
    dto.setContent(verification.getContent());
    return dto;
  }
}
