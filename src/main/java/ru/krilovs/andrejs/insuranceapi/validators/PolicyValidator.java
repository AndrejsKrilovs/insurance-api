package ru.krilovs.andrejs.insuranceapi.validators;

import org.springframework.stereotype.Component;

import ru.krilovs.andrejs.insuranceapi.entity.*;

import java.util.stream.Stream;

@Component
public class PolicyValidator {
    public boolean validate(Policy policy) {
        return !policy.getUserName().isBlank() &&
                Stream.of(Status.values()).anyMatch(status -> status.equals(policy.getPolicyStatus())) &&
                policy.getPolicyObjects().size() > 0 &&
                policy.getPolicyObjects().stream().anyMatch(this::validateObject);
    }

    private boolean validateSubObject(PolicySubObject subObject) {
        return !subObject.getName().isBlank() &&
                subObject.getInsuredSum() > 0 &&
                Stream.of(RiskType.values()).anyMatch(riskType -> riskType.equals(subObject.getRiskType()));
    }

    private boolean validateObject(PolicyObject policyObject) {
        return !policyObject.getName().isBlank() &&
                policyObject.getSubObjects().size() > 0 &&
                policyObject.getSubObjects().stream().anyMatch(this::validateSubObject);
    }
}
