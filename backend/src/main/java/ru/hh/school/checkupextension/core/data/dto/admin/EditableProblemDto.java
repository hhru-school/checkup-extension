package ru.hh.school.checkupextension.core.data.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import ru.hh.school.checkupextension.utils.constant.ProblemDtoJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditableProblemDto {
  public final long id;
  public final String type;
  public final String title;
  public final String description;
  public final String content;
  public final byte maxAttempts;
  public final boolean active;
  public final String htmlPartSolution;
  public final String cssPartSolution;
  public final String jsPartSolution;
  public final String htmlTemplate;
  public final String cssTemplate;
  public final String jsTemplate;
  public final List<EditableVerificationDto> test;

  @JsonCreator
  public EditableProblemDto(
      @JsonProperty(ProblemDtoJsonPropertyName.ID) long problemId,
      @JsonProperty(ProblemDtoJsonPropertyName.TYPE) String type,
      @JsonProperty(ProblemDtoJsonPropertyName.TITLE) String title,
      @JsonProperty(ProblemDtoJsonPropertyName.DESCRIPTION) String description,
      @JsonProperty(ProblemDtoJsonPropertyName.CONTENT) String content,
      @JsonProperty(ProblemDtoJsonPropertyName.MAX_ATTEMPTS) byte maxAttempts,
      @JsonProperty(ProblemDtoJsonPropertyName.ACTIVE) boolean active,
      @JsonProperty(ProblemDtoJsonPropertyName.HTML_SOLUTION) String htmlPartSolution,
      @JsonProperty(ProblemDtoJsonPropertyName.CSS_SOLUTION) String cssPartSolution,
      @JsonProperty(ProblemDtoJsonPropertyName.JS_SOLUTION) String jsPartSolution,
      @JsonProperty(ProblemDtoJsonPropertyName.HTML_TEMPLATE) String htmlTemplate,
      @JsonProperty(ProblemDtoJsonPropertyName.CSS_TEMPLATE) String cssTemplate,
      @JsonProperty(ProblemDtoJsonPropertyName.JS_TEMPLATE) String jsTemplate,
      @JsonProperty(ProblemDtoJsonPropertyName.TEST) List<EditableVerificationDto> test
  ) {
    this.id = problemId;
    this.type = type;
    this.title = title;
    this.description = description;
    this.content = content;
    this.maxAttempts = maxAttempts;
    this.active = active;
    this.htmlPartSolution = htmlPartSolution;
    this.cssPartSolution = cssPartSolution;
    this.jsPartSolution = jsPartSolution;
    this.htmlTemplate = htmlTemplate;
    this.cssTemplate = cssTemplate;
    this.jsTemplate = jsTemplate;
    this.test = test;
  }

  @Override
  public String toString() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      var msg = "Unable to stringify %d %s %s";
      return String.format(msg, id, title, description);
    }
  }
}
