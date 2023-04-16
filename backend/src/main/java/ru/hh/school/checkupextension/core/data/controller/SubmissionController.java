package ru.hh.school.checkupextension.core.data.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.school.checkupextension.core.data.dto.SubmissionDto;
import ru.hh.school.checkupextension.core.data.request.SubmissionRequestDto;
import ru.hh.school.checkupextension.core.data.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
  private final SubmissionService submissionService;

  public SubmissionController(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  /**
   * Метод getById() обрабатывает GET-запрос по адресу "/api/submissions/{id}" и возвращает сущность
   * SubmissionDto с указанным идентификатором.
   */
  @GetMapping("/{id}")
  public SubmissionDto getById(@PathVariable Long id) {
    return submissionService.getById(id);
  }

  /**
   * Метод getAll() обрабатывает GET-запрос по адресу "/api/submissions"
   * и возвращает список всех сущностей типа SubmissionDto.
   */
  @GetMapping
  public List<SubmissionDto> getAll() {
    return submissionService.getAll();
  }

  /**
   * Метод create() обрабатывает POST-запрос по адресу "/api/submissions"
   * и создает новую сущность типа SubmissionDto на основе полученной в теле запроса информации.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SubmissionDto create(@RequestBody @Valid SubmissionRequestDto requestDto) {
    return submissionService.create(requestDto);
  }

  /**
   * Метод update() обрабатывает PUT-запрос по адресу "/api/submissions/{id}"
   * и обновляет существующую сущность типа SubmissionDto с указанным идентификатором.
   */
  @PutMapping("/{id}")
  public SubmissionDto update(@PathVariable Long id, @RequestBody @Valid SubmissionRequestDto requestDto) {
    return submissionService.update(id, requestDto);
  }

  /**
   * Метод delete() обрабатывает DELETE-запрос по адресу "/api/submissions/{id}"
   * и удаляет существующую сущность типа SubmissionDto с указанным идентификатором.
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    submissionService.delete(id);
  }
}
