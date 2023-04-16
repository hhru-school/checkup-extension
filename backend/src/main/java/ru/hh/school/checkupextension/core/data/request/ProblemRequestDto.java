package ru.hh.school.checkupextension.core.data.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * ProblemRequestDto - это класс, который служит для передачи информации о новой или
 * обновленной сущности Problem между контроллером и сервисом.
 */
public class ProblemRequestDto {
  @NotBlank(message = "Condition cannot be blank")
  private String condition;

  @NotBlank(message = "Type cannot be blank")
  private String type;

  @NotNull(message = "Max attempts cannot be null")
  @Min(value = 1, message = "Max attempts must be greater than or equal to 1")
  private Integer maxAttempts;

  public ProblemRequestDto() {
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