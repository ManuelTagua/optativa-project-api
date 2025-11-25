package com.example.ProyectoOptativa.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Integer id;

    @Column(name = "tech_name", length = 45, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "technologies")
    @JsonIgnore  
    private Set<Project> projects = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
