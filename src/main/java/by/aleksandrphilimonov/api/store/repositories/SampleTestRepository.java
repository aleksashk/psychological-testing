package by.aleksandrphilimonov.api.store.repositories;

import by.aleksandrphilimonov.api.store.entities.SampleTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleTestRepository extends JpaRepository<SampleTestEntity, Integer> {
}