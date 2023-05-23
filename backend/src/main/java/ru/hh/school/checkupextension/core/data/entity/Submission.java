package ru.hh.school.checkupextension.core.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "submission")
public class Submission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "submission_id")
  private Long id;

  @Column(name = "user_id")
  private Long user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id")
  private Problem problem;

  @Column(name = "creation_datetime")
  private LocalDateTime creationDateTime;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "solution")
  private JsonContainer userSolution;

  @Column(name = "status")
  private byte status;

  public Submission() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUser() {
    return user;
  }

  public void setUser(Long user) {
    this.user = user;
  }

  public Problem getProblem() {
    return problem;
  }

  public void setProblem(Problem problem) {
    this.problem = problem;
  }

  public JsonContainer getUserSolution() {
    return userSolution;
  }

  public void setUserSolution(JsonContainer userSolution) {
    this.userSolution = userSolution;
  }

  public byte getStatus() {
    return status;
  }

  public void setStatus(byte status) {
    this.status = status;
  }

  public LocalDateTime getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(LocalDateTime creationDateTime) {
    this.creationDateTime = creationDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Submission that = (Submission) o;
    return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(problem, that.problem)
        && Objects.equals(userSolution, that.userSolution) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, problem, userSolution, status);
  }
}
