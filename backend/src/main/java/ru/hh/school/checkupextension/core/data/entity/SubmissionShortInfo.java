package ru.hh.school.checkupextension.core.data.entity;

import java.time.LocalDateTime;

public record SubmissionShortInfo(Long submissionId, LocalDateTime creationDateTime, byte status) { }
