package ru.hh.school.checkupextension.core.data.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.school.checkupextension.utils.constant.TaskDtoJsonPropertyName;

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
      @JsonProperty(TaskDtoJsonPropertyName.ID) long taskId,
      @JsonProperty(TaskDtoJsonPropertyName.TYPE_ID) String type,
      @JsonProperty(TaskDtoJsonPropertyName.TITLE) String title,
      @JsonProperty(TaskDtoJsonPropertyName.DESCRIPTION) String description,
      @JsonProperty(TaskDtoJsonPropertyName.HTML_PART) String htmlPart,
      @JsonProperty(TaskDtoJsonPropertyName.CSS_PART) String cssPart,
      @JsonProperty(TaskDtoJsonPropertyName.JS_PART) String jsPart,
      @JsonProperty(TaskDtoJsonPropertyName.HTML_TEMPLATE) String htmlTemplate,
      @JsonProperty(TaskDtoJsonPropertyName.CSS_TEMPLATE) String cssTemplate,
      @JsonProperty(TaskDtoJsonPropertyName.JS_TEMPLATE) String jsTemplate,
      @JsonProperty(TaskDtoJsonPropertyName.TEST) List<EditableVerificationDto> test
  ) {
    this.Id = taskId;
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
