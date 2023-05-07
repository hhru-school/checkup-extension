package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.utils.constant.SubmissionJsonPropertyName;

public record ContestSubmissionResult(Long id, String status) {
    @JsonCreator
    public ContestSubmissionResult(
            @JsonProperty(SubmissionJsonPropertyName.ID) Long id,
            @JsonProperty(SubmissionJsonPropertyName.STATUS_ID) String status) {
        this.id = id;
        this.status = status;
    }
}
