package ru.hh.school.checkupextension.core.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * SubmissionRequestDto - это класс, который служит для передачи информации о новой или
 * обновленной сущности Submission между контроллером и сервисом.
 */
public class SubmissionRequestDto {
  @NotNull(message = "User ID cannot be null")
  private Long userId;

  @NotNull(message = "Problem ID cannot be null")
  private Long problemId;

  @NotBlank(message = "Solution cannot be blank")
  private String solution;

  public SubmissionRequestDto() {
  }

  public SubmissionRequestDto(Long userId, Long problemId, String solution) {
    this.userId = userId;
    this.problemId = problemId;
    this.solution = solution;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getProblemId() {
    return problemId;
  }

  public void setProblemId(Long problemId) {
    this.problemId = problemId;
  }

  public String getSolution() {
    return solution;
  }

  public void setSolution(String solution) {
    this.solution = solution;
  }
}
