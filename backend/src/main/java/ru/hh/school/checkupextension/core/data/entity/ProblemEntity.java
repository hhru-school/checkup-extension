package ru.hh.school.checkupextension.core.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.hh.school.checkupextension.core.data.Problem;
import ru.hh.school.checkupextension.core.data.pojo.MyJson;

import java.util.Objects;


@Entity
@Table(name = "problem")
public class ProblemEntity implements Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private byte type;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "active")
    private boolean active;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "template", columnDefinition = "jsonb")
    private MyJson template;

    public ProblemEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public byte getType() {
        return type;
    }

    @Override
    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public MyJson getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(MyJson template) {
        this.template = template;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemEntity that = (ProblemEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type)
                && Objects.equals(title, that.title) && Objects.equals(description, that.description)
                && Objects.equals(content, that.content) && Objects.equals(active, that.active)
                && Objects.equals(template, that.template);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, description, content, active, template);
    }
}
