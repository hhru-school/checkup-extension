package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.utils.constant.ProblemJsonPropertyName;

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
          @JsonProperty(ProblemJsonPropertyName.ID) long id,
          @JsonProperty(ProblemJsonPropertyName.TITLE) String title,
          @JsonProperty(ProblemJsonPropertyName.DESCRIPTION) String description,
          @JsonProperty(ProblemJsonPropertyName.CONTENT) String content,
          @JsonProperty(ProblemJsonPropertyName.TYPE) String type,
          @JsonProperty(ProblemJsonPropertyName.MAX_ATTEMPTS) byte maxAttempts,
          @JsonProperty(ProblemJsonPropertyName.ACTIVE) boolean active,
          @JsonProperty(ProblemJsonPropertyName.HTML_PART) String htmlPart,
          @JsonProperty(ProblemJsonPropertyName.CSS_PART) String cssPart,
          @JsonProperty(ProblemJsonPropertyName.JS_PART) String jsPart) {
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
