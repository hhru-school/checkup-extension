package ru.hh.school.checkupextension.core.data.dto;

/**
 * ProblemDto - это класс, который служит для передачи информации о сущности Problem между слоями приложения.
 */
public class ProblemDto {
  private Long id;
  private String condition;
  private String type;
  private Integer maxAttempts;

  public ProblemDto() {
  }

  public ProblemDto(Long id, String condition, String type, Integer maxAttempts) {
    this.id = id;
    this.condition = condition;
    this.type = type;
    this.maxAttempts = maxAttempts;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getMaxAttempts() {
    return maxAttempts;
  }

  public void setMaxAttempts(Integer maxAttempts) {
    this.maxAttempts = maxAttempts;
  }
}
