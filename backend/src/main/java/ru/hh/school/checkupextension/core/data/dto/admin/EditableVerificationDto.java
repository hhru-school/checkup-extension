package ru.hh.school.checkupextension.core.data.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.utils.constant.VerificationDtoJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EditableVerificationDto(Long id, String content) {
  @JsonCreator
  public EditableVerificationDto(
      @JsonProperty(VerificationDtoJsonPropertyName.ID) Long id,
      @JsonProperty(VerificationDtoJsonPropertyName.CONTENT) String content
  ) {
    this.id = id;
    this.content = content;
  }
}
