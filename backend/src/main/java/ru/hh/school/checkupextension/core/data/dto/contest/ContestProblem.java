package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.core.data.pojo.MyJson;

public class ContestProblem {
    public final Long id;
    public final String title;
    public final String description;
    public final String content;
    public final byte type;
    public final boolean active;
    public final MyJson template;

    @JsonCreator
    public ContestProblem(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("content") String content,
            @JsonProperty("type") byte type,
            @JsonProperty("active") boolean active,
            @JsonProperty("template") MyJson template) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.type = type;
        this.active = active;
        this.template = template;
    }
}
