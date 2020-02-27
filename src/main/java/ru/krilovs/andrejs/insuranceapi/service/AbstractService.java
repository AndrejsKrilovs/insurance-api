package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AbstractService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T add(T item);

    T modify(Long id, T item);

    void delete(Long id);
}
