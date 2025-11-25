package com.example.ProyectoOptativa.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private Integer id;

    @Column(name = "devname", length = 20, nullable = false)
    private String name;

    @Column(name = "dev_surname", length = 60, nullable = false)
    private String surname;

    @Column(length = 90)
    private String email;

    @Column(name = "linkedin_url", length = 90)
    private String linkedinUrl;

    @Column(name = "github_url", length = 90)
    private String githubUrl;

    @ManyToMany(mappedBy = "developers")
    @JsonIgnore  // para evitar bucles al serializar
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
