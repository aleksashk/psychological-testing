package by.aleksandrphilimonov.store.repositories;

import by.aleksandrphilimonov.store.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {
}
