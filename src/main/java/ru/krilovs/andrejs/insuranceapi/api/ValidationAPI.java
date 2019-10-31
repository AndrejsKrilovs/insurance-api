package ru.krilovs.andrejs.insuranceapi.api;

import org.springframework.stereotype.Service;

import ru.krilovs.andrejs.insuranceapi.entity.*;

import java.util.stream.Stream;

@Service
public class ValidationAPI {
    private boolean validateSubObject(final PolicySubObject subObject) {
        return !subObject.getName().isBlank() &&
                subObject.getInsuredSum() > 0 &&
                Stream.of(RiskType.values()).anyMatch(riskType -> riskType.equals(subObject.getRiskType()));
    }

    private boolean validateObject(final PolicyObject policyObject) {
        return !policyObject.getName().isBlank() &&
                policyObject.getSubObjects().size() > 0 &&
                policyObject.getSubObjects().stream().anyMatch(policySubObject -> validateSubObject(policySubObject));
    }

    public boolean validatePolice(final Policy policy) {
        return !policy.getUserName().isBlank() &&
                Stream.of(Status.values()).anyMatch(status -> status.equals(policy.getPolicyStatus())) &&
                policy.getPolicyObjects().size() > 0 &&
                policy.getPolicyObjects().stream().anyMatch(policyObject -> validateObject(policyObject));
    }
}
