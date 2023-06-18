package ru.hh.school.checkupextension.core.data.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import ru.hh.school.checkupextension.utils.constant.ProblemDtoJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortEditableProblemDto {
  public final Long id;
  public final List<EditableVerificationDto> verificationDtoList;

  @JsonCreator
  public ShortEditableProblemDto(
      @JsonProperty(ProblemDtoJsonPropertyName.ID) Long id,
      @JsonProperty(ProblemDtoJsonPropertyName.TEST) List<EditableVerificationDto> verificationDtoList
  ) {
    this.id = id;
    this.verificationDtoList = verificationDtoList;
  }
}
