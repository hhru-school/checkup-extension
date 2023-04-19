package ru.hh.school.checkupextension.core.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.hh.school.checkupextension.core.data.Problem;

import java.util.Objects;


@Entity
@Table(name = "problem")
public class ProblemEntity implements Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "condition")
    private String condition;

    @Column(name = "type")
    private byte type;

    @Column(name = "max_attempts")
    private Short maxAttempts;

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
    public String getCondition() {
        return condition;
    }

    @Override
    public void setCondition(String condition) {
        this.condition = condition;
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
    public Short getMaxAttempts() {
        return maxAttempts;
    }

    @Override
    public void setMaxAttempts(Short maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemEntity that = (ProblemEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(condition, that.condition) && Objects.equals(type, that.type)
                && Objects.equals(maxAttempts, that.maxAttempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, condition, type, maxAttempts);
    }
}
