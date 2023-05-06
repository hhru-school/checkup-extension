package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.core.data.dto.SubmissionJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContestSubmission {
    public final long id;
    public final long problemId;
    public final String status;
    public final String htmlPart;
    public final String cssPart;
    public final String jsPart;

    @JsonCreator
    public ContestSubmission(
            @JsonProperty(SubmissionJsonPropertyName.ID) long id,
            @JsonProperty(SubmissionJsonPropertyName.TASK_ID) Long problemId,
            @JsonProperty(SubmissionJsonPropertyName.STATUS_ID) String status,
            @JsonProperty(SubmissionJsonPropertyName.HTML_CONTENT) String htmlContent,
            @JsonProperty(SubmissionJsonPropertyName.CSS_CONTENT) String cssContent,
            @JsonProperty(SubmissionJsonPropertyName.JS_CONTENT) String jsContent) {
        this.id = id;
        this.problemId = problemId;
        this.status = status;
        this.htmlPart = htmlContent;
        this.cssPart = cssContent;
        this.jsPart = jsContent;
    }
}
