package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class PolicyObject {
    private Long id;
    private String name;
    private Policy policy;
}
