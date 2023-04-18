package ru.hh.school.checkupextension.core.data.dto;

/**
 * ProblemDto - это класс, который служит для передачи информации о сущности Problem между слоями приложения.
 */
public class ProblemDto {
  private Long id;
  private String condition;
  private byte type;
  private Short maxAttempts;

  public ProblemDto() {
  }

  public ProblemDto(Long id, String condition, byte type, Short maxAttempts) {
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

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public Short getMaxAttempts() {
    return maxAttempts;
  }

  public void setMaxAttempts(Short maxAttempts) {
    this.maxAttempts = maxAttempts;
  }
}
