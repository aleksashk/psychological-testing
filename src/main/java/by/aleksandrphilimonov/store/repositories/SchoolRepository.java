package by.aleksandrphilimonov.store.repositories;

import by.aleksandrphilimonov.store.entities.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<SchoolEntity, Integer> {
    Optional<SchoolEntity> findByName(String name);
}