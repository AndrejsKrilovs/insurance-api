package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "riskType"})
public class PolicySubObject {
    private Long id;
    private String name;
    private RiskType riskType;
    private BigDecimal insuredAmount;
}
