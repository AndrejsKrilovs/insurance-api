package ru.krilovs.andrejs.insuranceapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.Status;
import ru.krilovs.andrejs.insuranceapi.repository.PolicyRepository;
import ru.krilovs.andrejs.insuranceapi.util.Calculator;
import ru.krilovs.andrejs.insuranceapi.util.Utils;
import ru.krilovs.andrejs.insuranceapi.util.Validator;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PolicyService implements AbstractService<Policy>{
    private final PolicyRepository repository;

    @Override
    public List<Policy> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Policy> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Policy add(Policy policy) {
        Validator.validatePolicy(policy);
        policy.setPremium(Calculator.calculatePremium(policy));
        policy.setStatus(Status.APPROVED);
        return repository.save(policy);
    }

    @Override
    public Policy modify(Long id, Policy policy) {
        Validator.validatePolicy(policy);
        return findById(id).map(item -> {
            Utils.copyPolicyProperties(policy, item);
            item.setPremium(Calculator.calculatePremium(item));
            item.setStatus(Status.APPROVED);
            return repository.update(item);
        }).orElse(new Policy());
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(repository::delete);
    }
}
