package ru.hh.school.checkupextension.core.data.dto.contest;

import java.time.LocalDateTime;

public record ContestSubmissionShortInfoDto(Long submissionId, LocalDateTime creationDateTime, String status) { }
