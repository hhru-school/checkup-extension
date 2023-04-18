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

import java.util.Objects;

@Entity
@Table(name = "submission")
public class Submission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Long user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "problem_id")
  private Problem problem;

  @Column(name = "solution")
  private String solution;

  @Column(name = "status")
  private String status;

  public Submission() {
  }

  public Submission(Long user, Problem problem, String solution, String status) {
    this.user = user;
    this.problem = problem;
    this.solution = solution;
    this.status = status;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Submission that = (Submission) o;
    return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(problem, that.problem)
        && Objects.equals(solution, that.solution) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, problem, solution, status);
  }
}
