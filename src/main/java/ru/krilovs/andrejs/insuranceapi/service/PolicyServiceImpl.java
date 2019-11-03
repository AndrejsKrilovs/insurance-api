package ru.krilovs.andrejs.insuranceapi.service;

import org.springframework.stereotype.Service;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.PolicyObject;
import ru.krilovs.andrejs.insuranceapi.entity.PolicySubObject;
import ru.krilovs.andrejs.insuranceapi.entity.RiskType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

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
        List<PolicySubObject> subObjectList = policy.getPolicyObjects().stream()
                .map(PolicyObject::getSubObjects)
                .findAny()
                .orElseThrow(RuntimeException::new);
        DoubleStream values = subObjectList
                .stream()
                .peek(this::setPremiumCalculationWithCoefficient)
                .map(PolicySubObject::getCalculatedPremium)
                .mapToDouble(Double::doubleValue);
        return values.sum();
    }

    @Override
    public Policy updatePolicyPrimaryData(Policy policy, String policyNumber, Double premium) {
        if(!policyNumber.isBlank()) {
            policy.setPolicyNumber(policyNumber);
        }

        if(premium != null && premium.doubleValue() > 0 && !premium.equals(policy.getPolicyPremium())) {
            policy.setPolicyPremium(BigDecimal.valueOf(premium));
        }

        return policy;
    }

    private void setPremiumCalculationWithCoefficient(PolicySubObject policySubObject) {
        if(policySubObject.getRiskType().equals(RiskType.FIRE) && policySubObject.getInsuredSum() > 100) {
            policySubObject.setCalculatedPremium(policySubObject.getInsuredSum() * 0.023);
        }

        else if(policySubObject.getRiskType().equals(RiskType.FIRE) && policySubObject.getInsuredSum() <= 100) {
            policySubObject.setCalculatedPremium(policySubObject.getInsuredSum() * 0.013);
        }

        else if(policySubObject.getRiskType().equals(RiskType.WATER) && policySubObject.getInsuredSum() >= 10) {
            policySubObject.setCalculatedPremium(policySubObject.getInsuredSum() * 0.05);
        }

        else if(policySubObject.getRiskType().equals(RiskType.WATER) && policySubObject.getInsuredSum() < 10) {
            policySubObject.setCalculatedPremium(policySubObject.getInsuredSum() * 0.1);
        }
    }
}
