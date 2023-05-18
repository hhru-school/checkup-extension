package ru.hh.school.checkupextension.core.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "problem")
public class Problem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "problem_id")
  private long id;

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
  @Column(name = "template", columnDefinition = "jsonb")
  private Template template;

  public Problem() {
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

  public Template getTemplate() {
    return template;
  }

  public void setTemplate(Template template) {
    this.template = template;
  }

  public byte getMaxAttempts() {
    return maxAttempts;
  }

  public void setMaxAttempts(byte maxAttempts) {
    this.maxAttempts = maxAttempts;
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

  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Template implements Serializable {

    @JsonProperty("html")
    private String htmlTemplate;
    @JsonProperty("css")
    private String cssTemplate;
    @JsonProperty("js")
    private String jsTemplate;

    public void setHtmlTemplate(String htmlTemplate) { this.htmlTemplate = htmlTemplate; }
    public String getHtmlTemplate() { return this.htmlTemplate; }

    public void setCssTemplate(String cssTemplate) { this.cssTemplate = cssTemplate; }
    public String getCssTemplate() { return this.cssTemplate; }

    public void setJsTemplate(String jsTemplate) { this.jsTemplate = jsTemplate; }
    public String getJsTemplate() { return this.jsTemplate; }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      var that = (Template) obj;
      return Objects.equals(this.htmlTemplate, that.htmlTemplate) &&
              Objects.equals(this.cssTemplate, that.cssTemplate) &&
              Objects.equals(this.jsTemplate, that.jsTemplate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.htmlTemplate, this.cssTemplate, this.jsTemplate);
    }
  }
}
