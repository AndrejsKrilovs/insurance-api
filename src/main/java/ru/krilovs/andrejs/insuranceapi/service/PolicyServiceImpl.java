package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.PolicyObject;
import ru.krilovs.andrejs.insuranceapi.entity.PolicySubObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergei Visotsky
 * @author Andrejs Krilovs
 */
@Service
public class PolicyServiceImpl implements PolicyService {
    private final List<Policy> data;

    public PolicyServiceImpl() {
        data = new ArrayList<>();
    }

    @Override
    public List<Policy> getAllPolicies() {
        return data;
    }

    @Override
    public Policy getPolicy(String policy) {
        return data.stream()
                .filter(item -> item.getPolicyNumber().equalsIgnoreCase(policy))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Double calculatePremium(Policy policy) {
        return policy.getPolicyObjects().stream()
                .map(PolicyObject::getSubObjects)
                .findAny()
                .orElseThrow(RuntimeException::new)
                .stream()
                .map(PolicySubObject::getInsuredSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public Policy updatePolicyPrimaryData(Policy policy, String policyNumber, Double premium) {
        if(!policyNumber.isBlank()) {
            policy.setPolicyNumber(policyNumber);
        }

        if(premium != null && premium > 0 && !premium.equals(policy.getPolicyPremium())) {
            policy.setPolicyPremium(premium);
        }

        return policy;
    }
}
