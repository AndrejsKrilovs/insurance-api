package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class Policy {
    private Long id;
    private String name;
    private Status status;
    private BigDecimal premium;
}
