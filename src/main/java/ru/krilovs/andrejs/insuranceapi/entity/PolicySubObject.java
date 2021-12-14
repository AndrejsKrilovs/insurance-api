package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name", "riskType"})
public class PolicySubObject {
    private Long id;
    private String name;
    private RiskType riskType;
    private BigDecimal insuredAmount;
    private PolicyObject policyObject;
}
