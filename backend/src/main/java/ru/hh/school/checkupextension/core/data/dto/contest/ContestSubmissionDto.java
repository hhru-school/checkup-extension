package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.utils.constant.SubmissionJsonPropertyName;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContestSubmissionDto {
  public final Long id;
  public final long problemId;
  public final String status;
  public final LocalDateTime creationDateTime;

  public final String htmlPart;
  public final String cssPart;
  public final String jsPart;

  @JsonCreator
  public ContestSubmissionDto(
      @JsonProperty(SubmissionJsonPropertyName.ID) Long id,
      @JsonProperty(SubmissionJsonPropertyName.TASK_ID) Long problemId,
      @JsonProperty(SubmissionJsonPropertyName.STATUS_ID) String status,
      @JsonProperty(SubmissionJsonPropertyName.CREATION_DATE_TIME) LocalDateTime creationDateTime,
      @JsonProperty(SubmissionJsonPropertyName.HTML_CONTENT) String htmlContent,
      @JsonProperty(SubmissionJsonPropertyName.CSS_CONTENT) String cssContent,
      @JsonProperty(SubmissionJsonPropertyName.JS_CONTENT) String jsContent
  ) {
    this.id = id;
    this.problemId = problemId;
    this.status = status;
    this.creationDateTime = creationDateTime;
    this.htmlPart = htmlContent;
    this.cssPart = cssContent;
    this.jsPart = jsContent;
  }
}
