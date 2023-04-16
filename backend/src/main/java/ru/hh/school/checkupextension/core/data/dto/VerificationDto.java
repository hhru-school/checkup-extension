package ru.hh.school.checkupextension.core.data.dto;

/**
 * VerificationDto - это класс, который служит для передачи информации о сущности Verification между слоями приложения.
 */
public class VerificationDto {
  private Long id;
  private Long problemId;
  private String content;

  public VerificationDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProblemId() {
    return problemId;
  }

  public void setProblemId(Long problemId) {
    this.problemId = problemId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
