package ru.krilovs.andrejs.insuranceapi.repository;

import org.springframework.stereotype.Repository;
import ru.krilovs.andrejs.insuranceapi.entity.Policy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PolicyDto implements PolicyRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Policy> findAll() {
        return null;
    }

    @Override
    public Optional<Policy> findById(Long id) {
        final Policy entity = (Policy) em.createNamedQuery("").getSingleResult();
        entity.getPolicyObjects().size();
        entity.getPolicyObjects().forEach(item -> item.getPolicySubObjects().size());
        return Optional.of(entity);
    }

    @Override
    @Transactional
    public Policy save(Policy policy) {
        em.persist(policy);
        return policy;
    }

    @Override
    @Transactional
    public Policy update(Policy policy) {
        em.merge(policy);
        return policy;
    }

    @Override
    @Transactional
    public void delete(Policy policy) {
        em.remove(policy);
    }
}
