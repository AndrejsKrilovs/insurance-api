package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "riskType"})
@Entity
@Table(schema = "sa")
public class PolicySubObject implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policy_sub_object_pk_sequence"
    )
    @SequenceGenerator(
            name = "policy_sub_object_pk_sequence",
            sequenceName = "policy_sub_object_pk_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private RiskType riskType;

    @Column(precision = 5, scale = 2)
    private BigDecimal insuredAmount;
}
