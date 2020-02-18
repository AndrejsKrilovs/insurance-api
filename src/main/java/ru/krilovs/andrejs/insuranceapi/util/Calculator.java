package ru.krilovs.andrejs.insuranceapi.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

import ru.krilovs.andrejs.insuranceapi.entity.Policy;

import java.math.BigDecimal;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Calculator {
    public static BigDecimal calculatePremium(Policy policy) {
        final double totalSum = policy.getPolicyObjects().stream()
                .flatMap(item -> item.getPolicySubObjects().stream())
                .mapToDouble(item -> item.getInsuredAmount().doubleValue())
                .sum();
        return BigDecimal.valueOf(totalSum);
    }
}
