package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.core.data.pojo.TemplatePojo;

public record ContestProblemDto(Long id, String title, String description, String content,
                                byte type, byte maxAttempts, boolean active, TemplatePojo template) {
  @JsonCreator
  public ContestProblemDto(
          @JsonProperty("id") Long id,
          @JsonProperty("title") String title,
          @JsonProperty("description") String description,
          @JsonProperty("content") String content,
          @JsonProperty("type") byte type,
          @JsonProperty("max_attempts") byte maxAttempts,
          @JsonProperty("active") boolean active,
          @JsonProperty("template") TemplatePojo template) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.content = content;
    this.type = type;
    this.maxAttempts = maxAttempts;
    this.active = active;
    this.template = template;
  }
}
