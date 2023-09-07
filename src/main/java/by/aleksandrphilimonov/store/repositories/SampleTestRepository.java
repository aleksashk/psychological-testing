package by.aleksandrphilimonov.store.repositories;

import by.aleksandrphilimonov.store.entities.SampleTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleTestRepository extends JpaRepository<SampleTestEntity, Integer> {
}