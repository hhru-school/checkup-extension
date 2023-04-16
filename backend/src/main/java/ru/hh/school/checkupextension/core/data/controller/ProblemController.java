package ru.hh.school.checkupextension.core.data.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.school.checkupextension.core.data.dto.ProblemDto;
import ru.hh.school.checkupextension.core.data.request.ProblemRequestDto;
import ru.hh.school.checkupextension.core.data.service.ProblemService;

import java.util.List;


@RestController
@RequestMapping("/api/problems")
public class ProblemController {
  private final ProblemService problemService;

  public ProblemController(ProblemService problemService) {
    this.problemService = problemService;
  }

  /**
   * Метод getById() обрабатывает GET-запрос по адресу "/api/problems/{id}" и возвращает сущность
   * ProblemDto с указанным идентификатором.
   */
  @GetMapping("/{id}")
  public ProblemDto getById(@PathVariable Long id) {
    return problemService.getById(id);
  }

  /**
   * Метод getAll() обрабатывает GET-запрос по адресу "/api/problems"
   * и возвращает список всех сущностей типа ProblemDto.
   */
  @GetMapping
  public List<ProblemDto> getAll() {
    return problemService.getAll();
  }

  /**
   * Метод create() обрабатывает POST-запрос по адресу "/api/problems"
   * и создает новую сущность типа ProblemDto на основе полученной в теле запроса информации.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProblemDto create(@RequestBody @Valid ProblemRequestDto requestDto) {
    return problemService.create(requestDto);
  }

  /**
   * Метод update() обрабатывает PUT-запрос по адресу "/api/problems/{id}"
   * и обновляет существующую сущность типа ProblemDto с указанным идентификатором.
   */
  @PutMapping("/{id}")
  public ProblemDto update(@PathVariable Long id, @RequestBody @Valid ProblemRequestDto requestDto) {
    return problemService.update(id, requestDto);
  }

  /**
   * Метод delete() обрабатывает DELETE-запрос по адресу "/api/problems/{id}"
   * и удаляет существующую сущность типа ProblemDto с указанным идентификатором.
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    problemService.delete(id);
  }
}
