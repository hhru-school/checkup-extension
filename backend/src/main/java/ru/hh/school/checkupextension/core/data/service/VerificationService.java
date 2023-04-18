package ru.hh.school.checkupextension.core.data.service;

import ru.hh.school.checkupextension.core.data.daoimpl.ProblemDaoImpl;
import ru.hh.school.checkupextension.core.data.daoimpl.VerificationDaoImpl;
import ru.hh.school.checkupextension.core.data.dto.VerificationDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Verification;
import ru.hh.school.checkupextension.utils.exception.ProblemNotFoundException;
import ru.hh.school.checkupextension.core.data.request.VerificationRequestDto;
import ru.hh.school.checkupextension.utils.exception.VerificationNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.checkupextension.utils.exception.constant.ExceptionConstants.PROBLEM_ID_NOT_FOUND_MESSAGE;
import static ru.hh.school.checkupextension.utils.exception.constant.ExceptionConstants.VERIFICATION_ID_NOT_FOUND_MESSAGE;

/**
 * VerificationService - это класс, который содержит бизнес-логику для работы с сущностями типа Verification и Problem.
 * Он реализует логику создания, чтения, обновления и удаления сущностей, а также любую другую бизнес-логику,
 * связанную с этими сущностями.
 */
public class VerificationService {
  private final VerificationDaoImpl verificationDao;
  private final ProblemDaoImpl problemDao;

  public VerificationService(VerificationDaoImpl verificationDao, ProblemDaoImpl problemDao) {
    this.verificationDao = verificationDao;
    this.problemDao = problemDao;
  }

  public VerificationDto getById(Long id) {
    Verification verification = verificationDao.getById(id);
    if (verification == null) {
      throw new VerificationNotFoundException(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id));
    }
    return mapToDto(verification);
  }

  public List<VerificationDto> getAll() {
    List<Verification> verifications = verificationDao.getAll();
    return verifications.stream()
        .map(this::mapToDto)
        .collect(Collectors.toList());
  }

  public VerificationDto create(VerificationRequestDto requestDto) {
    Problem problem = problemDao.getById(requestDto.getProblemId());
    if (problem == null) {
      throw new ProblemNotFoundException(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId()));
    }
    Verification verification = mapToEntity(requestDto, problem);
    verificationDao.create(verification);
    return mapToDto(verification);
  }

  public VerificationDto update(Long id, VerificationRequestDto requestDto) {
    Verification verification = verificationDao.getById(id);
    if (verification == null) {
      throw new VerificationNotFoundException(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id));
    }
    Problem problem = problemDao.getById(requestDto.getProblemId());
    if (problem == null) {
      throw new ProblemNotFoundException(String.format(PROBLEM_ID_NOT_FOUND_MESSAGE, requestDto.getProblemId()));
    }
    verification.setProblem(problem);
    verification.setContent(requestDto.getContent());
    verificationDao.update(verification);
    return mapToDto(verification);
  }

  public void delete(Long id) {
    Verification verification = verificationDao.getById(id);
    if (verification == null) {
      throw new VerificationNotFoundException(String.format(VERIFICATION_ID_NOT_FOUND_MESSAGE, id));
    }
    verificationDao.delete(id);
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
