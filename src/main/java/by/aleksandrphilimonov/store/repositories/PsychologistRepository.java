package by.aleksandrphilimonov.store.repositories;

import by.aleksandrphilimonov.store.entities.PsychologistEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsychologistRepository extends JpaRepository<PsychologistEntity, Integer> {
    Optional<PsychologistEntity> findTopByLoginAndPassword(@NonNull String login, @NonNull String password);
}
