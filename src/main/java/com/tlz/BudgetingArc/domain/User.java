package com.tlz.BudgetingArc.domain;

import jakarta.persistence.*;

import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String username;
    private String password;
    private Set<Budget> budgets = new TreeSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Budget> getGroups() {
        return budgets;
    }

    public void setGroups(Set<Budget> budgets) {
        this.budgets = budgets;
    }
}