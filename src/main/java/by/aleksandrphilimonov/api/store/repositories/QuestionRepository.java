package by.aleksandrphilimonov.api.store.repositories;

import by.aleksandrphilimonov.api.store.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
}