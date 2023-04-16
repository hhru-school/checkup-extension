package ru.hh.school.checkupextension.core.data.dto;

/**
 * SubmissionDto - это класс, который служит для передачи информации о сущности Submission между слоями приложения.
 */
public class SubmissionDto {
  private Long id;
  private Long userId;
  private Long problemId;
  private String solution;
  private String status;

  public SubmissionDto() {
  }

  public SubmissionDto(Long id, Long userId, Long problemId, String solution, String status) {
    this.id = id;
    this.userId = userId;
    this.problemId = problemId;
    this.solution = solution;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
