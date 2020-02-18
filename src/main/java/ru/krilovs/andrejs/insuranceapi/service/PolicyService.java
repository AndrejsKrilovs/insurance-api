package ru.krilovs.andrejs.insuranceapi.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.Status;
import ru.krilovs.andrejs.insuranceapi.util.Calculator;
import ru.krilovs.andrejs.insuranceapi.util.Validator;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PolicyService {
    private final List<Policy> policies;

    public List<Policy> findAllPolicies() {
        return policies;
    }

    public Optional<Policy> findPolicyById(Long id) {
        return policies.stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst();
    }

    public Policy addPolicy(Policy policyToAdd) {
        Validator.validatePolicy(policyToAdd);
        policyToAdd.setId((long) policies.size() + 1);
        policyToAdd.setStatus(Status.APPROVED);
        policyToAdd.setPremium(Calculator.calculatePremium(policyToAdd));
        policies.add(policyToAdd);
        return policyToAdd;
    }

    public Policy modifyPolicy(Long index, Policy policyToModify) {
        Validator.validatePolicy(policyToModify);
        final Optional<Policy> policyFromDB = findPolicyById(index);
        return policyFromDB.map(item -> {
            BeanUtils.copyProperties(policyToModify, item, "id");
            policies.set(policies.indexOf(item), item);
            return item;
        }).orElse(new Policy());
    }

    public void deletePolicy(Long id) {
        findPolicyById(id).ifPresent(policies::remove);
    }
}
