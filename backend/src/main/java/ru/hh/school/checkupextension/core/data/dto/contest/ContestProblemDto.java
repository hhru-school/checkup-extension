package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.utils.constant.ProblemDtoJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContestProblemDto {
  public final long id;
  public final String title;
  public final String description;
  public final String content;
  public final String type;
  public final byte maxAttempts;
  public final boolean active;
  public final String htmlPart;
  public final String cssPart;
  public final String jsPart;

  @JsonCreator
  public ContestProblemDto(
          @JsonProperty(ProblemDtoJsonPropertyName.ID) long id,
          @JsonProperty(ProblemDtoJsonPropertyName.TITLE) String title,
          @JsonProperty(ProblemDtoJsonPropertyName.DESCRIPTION) String description,
          @JsonProperty(ProblemDtoJsonPropertyName.CONTENT) String content,
          @JsonProperty(ProblemDtoJsonPropertyName.TYPE_ID) String type,
          @JsonProperty(ProblemDtoJsonPropertyName.MAX_ATTEMPTS) byte maxAttempts,
          @JsonProperty(ProblemDtoJsonPropertyName.ACTIVE) boolean active,
          @JsonProperty(ProblemDtoJsonPropertyName.HTML_TEMPLATE) String htmlPart,
          @JsonProperty(ProblemDtoJsonPropertyName.CSS_TEMPLATE) String cssPart,
          @JsonProperty(ProblemDtoJsonPropertyName.JS_TEMPLATE) String jsPart) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.content = content;
    this.type = type;
    this.maxAttempts = maxAttempts;
    this.active = active;
    this.htmlPart = htmlPart;
    this.cssPart = cssPart;
    this.jsPart = jsPart;
  }
}
