package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContestProblem {
    public final Long id;
    public final String condition;
    public final byte type;
    public final byte maxAttempts;

    @JsonCreator
    public ContestProblem(
            @JsonProperty("Id") Long id,
            @JsonProperty("Condition") String condition,
            @JsonProperty("Type") byte type,
            @JsonProperty("MaxAttempts") byte maxAttempts) {
        this.id = id;
        this.condition = condition;
        this.type = type;
        this.maxAttempts = maxAttempts;
    }
}
