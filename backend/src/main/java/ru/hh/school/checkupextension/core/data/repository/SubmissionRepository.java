package ru.hh.school.checkupextension.core.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hh.school.checkupextension.core.data.entity.Submission;

/**
 * В этих интерфейсах мы наследуемся от JpaRepository и указываем тип сущности и тип идентификатора сущности (Long).
 * Теперь у нас есть возможность использовать методы, предоставляемые JpaRepository,
 * такие как findAll(), findById(), save(), deleteById(), и другие. Если необходимо,
 * можно добавить собственные методы, специфичные для работы с соответствующей сущностью.
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
