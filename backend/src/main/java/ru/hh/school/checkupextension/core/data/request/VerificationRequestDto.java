package ru.hh.school.checkupextension.core.data.request;

public class VerificationRequestDto {
  private Long problemId;
  private String content;

  public VerificationRequestDto() {
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
