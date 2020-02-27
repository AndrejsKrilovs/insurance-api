package ru.krilovs.andrejs.insuranceapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;
import ru.krilovs.andrejs.insuranceapi.entity.PolicySubObject;

import java.math.BigDecimal;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Calculator {
    public static BigDecimal calculatePremium(Policy policy) {
        return policy.getPolicyObjects().stream()
                .flatMap(item -> item.getPolicySubObjects().stream())
                .map(PolicySubObject::getInsuredAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
