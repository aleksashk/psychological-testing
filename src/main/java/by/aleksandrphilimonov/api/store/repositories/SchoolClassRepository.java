package by.aleksandrphilimonov.api.store.repositories;

import by.aleksandrphilimonov.api.store.entities.SchoolClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClassEntity, Integer> {
    Optional<SchoolClassEntity> findByName(String name);
}
