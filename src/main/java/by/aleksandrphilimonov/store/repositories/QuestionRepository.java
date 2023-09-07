package by.aleksandrphilimonov.store.repositories;

import by.aleksandrphilimonov.store.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
}