package ru.hh.school.checkupextension.core.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "problem")
public class Problem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "problem_id")
  private Long id;

  @Column(name = "type")
  private byte type;

  @Column(name = "max_attempts")
  private byte maxAttempts;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "content")
  private String content;

  @Column(name = "active")
  private boolean active;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "solution")
  private JsonContainer referenceSolution;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "template", columnDefinition = "jsonb")
  private JsonContainer template;

  @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Verification> verifications;

  @Deprecated
  public Problem() {
    this.verifications = new ArrayList<>();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public JsonContainer getReferenceSolution() { return referenceSolution; }
  public void setReferenceSolution(JsonContainer referenceSolution) { this.referenceSolution = referenceSolution; }

  public JsonContainer getTemplate() {
    return template;
  }

  public void setTemplate(JsonContainer template) {
    this.template = template;
  }

  public byte getMaxAttempts() {
    return maxAttempts;
  }

  public void setMaxAttempts(byte maxAttempts) {
    this.maxAttempts = maxAttempts;
  }

  public List<Verification> getVerifications() {
    return verifications;
  }

  public void setVerifications(List<Verification> verifications) {
    this.verifications = verifications;
    verifications.forEach(v -> v.setProblem(this));
  }

  public void addVerification(Verification verification) {
    verifications.add(verification);
    verification.setProblem(this);
  }

  public void removeVerification(Verification verification) {
    verifications.remove(verification);
    verification.setProblem(null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Problem problem = (Problem) o;
    return type == problem.type && maxAttempts == problem.maxAttempts && active == problem.active &&
            Objects.equals(id, problem.id) && Objects.equals(title, problem.title) &&
            Objects.equals(description, problem.description) && Objects.equals(content, problem.content) &&
            Objects.equals(template, problem.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, maxAttempts, title, description, content, active, template);
  }
}
