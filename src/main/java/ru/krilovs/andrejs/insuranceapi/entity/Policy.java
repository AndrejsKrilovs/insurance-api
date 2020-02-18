package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Policy {
    private Long id;
    private String name;
    private BigDecimal premium;
    private Status status;
    private Set<PolicyObject> policyObjects;
}
