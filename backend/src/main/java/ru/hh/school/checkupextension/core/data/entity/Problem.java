package ru.hh.school.checkupextension.core.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;


@Entity
@Table(name = "problem")
public class Problem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "condition")
  private String condition;

  @Column(name = "type")
  private String type;

  @Column(name = "max_attempts")
  private Integer maxAttempts;

  public Problem() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Problem that = (Problem) o;
    return Objects.equals(id, that.id) && Objects.equals(condition, that.condition) && Objects.equals(type, that.type)
        && Objects.equals(maxAttempts, that.maxAttempts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, condition, type, maxAttempts);
  }
}
