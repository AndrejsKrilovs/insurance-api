package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
public class PolicyObject {
    private Long id;
    private String name;
    private Set<PolicySubObject> policySubObjects;
}
