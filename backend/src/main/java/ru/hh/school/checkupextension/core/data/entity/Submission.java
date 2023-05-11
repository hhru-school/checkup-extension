package ru.hh.school.checkupextension.core.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  private UserSolution solution;

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

  public UserSolution getSolution() {
    return solution;
  }

  public void setSolution(UserSolution solution) {
    this.solution = solution;
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
        && Objects.equals(solution, that.solution) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user, problem, solution, status);
  }

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class UserSolution {
    @JsonProperty("html")
    private String htmlPart;
    @JsonProperty("css")
    private String cssPart;
    @JsonProperty("js")
    private String jsPart;

    public void setHtmlPart(String htmlPart) {
      this.htmlPart = htmlPart;
    }

    public String getHtmlPart() {
      return this.htmlPart;
    }

    public void setCssPart(String cssPart) {
      this.cssPart = cssPart;
    }

    public String getCssPart() {
      return this.cssPart;
    }

    public void setJsPart(String jsPart) {
      this.jsPart = jsPart;
    }

    public String getJsPart() {
      return this.jsPart;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }
      var that = (UserSolution) obj;
      return Objects.equals(this.htmlPart, that.htmlPart) &&
          Objects.equals(this.cssPart, that.cssPart) &&
          Objects.equals(this.jsPart, that.jsPart);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.htmlPart, this.cssPart, this.jsPart);
    }
  }
}
