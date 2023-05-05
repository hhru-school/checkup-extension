package ru.hh.school.checkupextension.core.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "submission")
public class SubmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private ProblemEntity problem;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "solution")
    private UserSolution solution;

    @Column(name = "status")
    private byte status;

    public SubmissionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public ProblemEntity getProblem() {
        return problem;
    }

    public void setProblem(ProblemEntity problem) {
        this.problem = problem;
    }

    public UserSolution getSolution() {
        return solution;
    }

    public void setSolution(UserSolution solution) {
        this.solution = solution;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionEntity that = (SubmissionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(problem, that.problem)
                && Objects.equals(solution, that.solution) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, problem, solution, status);
    }

    public static class UserSolution {
        private String htmlPart;
        private String cssPart;
        private String jsPart;

        public Optional<String> getHtmlPart() { return Optional.ofNullable(this.htmlPart); }
        public Optional<String> getCssPart() { return Optional.ofNullable(this.cssPart); }
        public Optional<String> getJsPart() { return Optional.of(this.jsPart); }
    }
}
