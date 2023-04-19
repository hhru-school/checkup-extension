package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContestSubmissionResult {
    public final Long id;
    public final String status;

    @JsonCreator
    public ContestSubmissionResult(
            @JsonProperty("SubmissionId") Long id,
            @JsonProperty("Status") String status) {
        this.id = id;
        this.status = status;
    }
}
