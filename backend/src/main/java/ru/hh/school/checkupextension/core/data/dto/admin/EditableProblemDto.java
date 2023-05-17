package ru.hh.school.checkupextension.core.data.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.school.checkupextension.utils.constant.ProblemDtoJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EditableProblemDto {
  public final long Id;
  public final String type;
  public final String title;
  public final String description;
  public final String htmlPart;
  public final String cssPart;
  public final String jsPart;
  public final String htmlTemplate;
  public final String cssTemplate;
  public final String jsTemplate;

  public final List<EditableVerificationDto> test;

  @JsonCreator
  public EditableProblemDto(
      @JsonProperty(ProblemDtoJsonPropertyName.ID) long problemId,
      @JsonProperty(ProblemDtoJsonPropertyName.TYPE_ID) String type,
      @JsonProperty(ProblemDtoJsonPropertyName.TITLE) String title,
      @JsonProperty(ProblemDtoJsonPropertyName.DESCRIPTION) String description,
      @JsonProperty(ProblemDtoJsonPropertyName.HTML_PART) String htmlPart,
      @JsonProperty(ProblemDtoJsonPropertyName.CSS_PART) String cssPart,
      @JsonProperty(ProblemDtoJsonPropertyName.JS_PART) String jsPart,
      @JsonProperty(ProblemDtoJsonPropertyName.HTML_TEMPLATE) String htmlTemplate,
      @JsonProperty(ProblemDtoJsonPropertyName.CSS_TEMPLATE) String cssTemplate,
      @JsonProperty(ProblemDtoJsonPropertyName.JS_TEMPLATE) String jsTemplate,
      @JsonProperty(ProblemDtoJsonPropertyName.TEST) List<EditableVerificationDto> test
  ) {
    this.Id = problemId;
    this.type = type;
    this.title = title;
    this.description = description;
    this.htmlPart = htmlPart;
    this.cssPart = cssPart;
    this.jsPart = jsPart;
    this.htmlTemplate = htmlTemplate;
    this.cssTemplate = cssTemplate;
    this.jsTemplate = jsTemplate;
    this.test = test;
  }
}
