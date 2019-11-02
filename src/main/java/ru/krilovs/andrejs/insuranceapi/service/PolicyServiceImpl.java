package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;
import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.exception.PolicyNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergei Visotsky
 * @author Andrejs Krilovs
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    private List<Policy> data = new ArrayList<>();

    @Override
    public List<Policy> getAllPolicies() {
        return data;
    }

    @Override
    public Policy getPolicy(String policy) {
        return data.stream()
                .filter(item -> item.getPolicyNumber().equalsIgnoreCase(policy))
                .findFirst()
                .orElseThrow(PolicyNotFoundException::new);
    }

    @Override
    public Double calculatePremium(Policy policy) {
        return policy.getPolicyObjects().stream()
                .map(item -> item.getSubObjects())
                .findAny()
                .get()
                .stream()
                .map(item -> item.getInsuredSum())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}
