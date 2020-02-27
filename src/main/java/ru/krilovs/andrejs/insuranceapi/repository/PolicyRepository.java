package ru.krilovs.andrejs.insuranceapi.repository;

import org.springframework.stereotype.Repository;
import ru.krilovs.andrejs.insuranceapi.entity.Policy;

import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyRepository {
    List<Policy> findAll();

    Optional<Policy> findById(Long id);

    Policy save(Policy policy);

    Policy update(Policy policy);

    void delete(Policy policy);
}
