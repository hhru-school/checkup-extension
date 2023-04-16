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
import ru.hh.school.checkupextension.core.data.dto.VerificationDto;
import ru.hh.school.checkupextension.core.data.request.VerificationRequestDto;
import ru.hh.school.checkupextension.core.data.service.VerificationService;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
public class VerificationController {
  private final VerificationService verificationService;

  public VerificationController(VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  /**
   * Метод getById() обрабатывает GET-запрос по адресу "/api/verifications/{id}" и возвращает сущность
   * VerificationDto с указанным идентификатором.
   */
  @GetMapping("/{id}")
  public VerificationDto getById(@PathVariable Long id) {
    return verificationService.getById(id);
  }

  /**
   * Метод getAll() обрабатывает GET-запрос по адресу "/api/verifications"
   * и возвращает список всех сущностей типа VerificationDto.
   */
  @GetMapping
  public List<VerificationDto> getAll() {
    return verificationService.getAll();
  }

  /**
   * Метод create() обрабатывает POST-запрос по адресу "/api/verifications"
   * и создает новую сущность типа VerificationDto на основе полученной в теле запроса информации.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VerificationDto create(@RequestBody @Valid VerificationRequestDto requestDto) {
    return verificationService.create(requestDto);
  }

  /**
   * Метод update() обрабатывает PUT-запрос по адресу "/api/verifications/{id}"
   * и обновляет существующую сущность типа VerificationDto с указанным идентификатором.
   */
  @PutMapping("/{id}")
  public VerificationDto update(@PathVariable Long id, @RequestBody @Valid VerificationRequestDto requestDto) {
    return verificationService.update(id, requestDto);
  }

  /**
   * Метод delete() обрабатывает DELETE-запрос по адресу "/api/verifications/{id}"
   * и удаляет существующую сущность типа VerificationDto с указанным идентификатором.
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    verificationService.delete(id);
  }
}