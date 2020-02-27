package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Entity
@Table(schema = "sa")
public class Policy implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policy_pk_sequence"
    )
    @SequenceGenerator(
            name = "policy_pk_sequence",
            sequenceName = "policy_pk_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Status status;

    @Transient
    private BigDecimal premium;

    @OneToMany(
            targetEntity = PolicyObject.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "policy",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "policy_object_fk")
    )
    private Set<PolicyObject> policyObjects;
}
