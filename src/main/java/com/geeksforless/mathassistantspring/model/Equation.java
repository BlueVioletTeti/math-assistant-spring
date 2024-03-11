package com.geeksforless.mathassistantspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "equation")
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String equation;
    @ManyToMany
    @Column(nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name = "equation_root",
            joinColumns = @JoinColumn(name = "equation_id"),
            inverseJoinColumns = @JoinColumn(name = "root_id"))
    private Set<Root> roots = new HashSet<>();

    public Equation(Long id) {
        this.id = id;
    }

    public Equation() {
    }
}
