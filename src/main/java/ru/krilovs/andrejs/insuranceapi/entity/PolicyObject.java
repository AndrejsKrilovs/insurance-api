package ru.krilovs.andrejs.insuranceapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@Entity
@Table(schema = "sa")
public class PolicyObject implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policy_object_pk_sequence"
    )
    @SequenceGenerator(
            name = "policy_object_pk_sequence",
            sequenceName = "policy_object_pk_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(
            targetEntity = PolicySubObject.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "policy_object",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "policy_sub_object_fk")
    )
    private Set<PolicySubObject> policySubObjects;
}
