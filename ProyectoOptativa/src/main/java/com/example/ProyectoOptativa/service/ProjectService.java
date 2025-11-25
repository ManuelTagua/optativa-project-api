package com.example.ProyectoOptativa.service;

import java.util.HashSet;
import java.util.List;

import com.example.ProyectoOptativa.model.entity.Project;
import com.example.ProyectoOptativa.model.repository.ProjectRepository;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> searchByWord(String word) {
        return projectRepository.findByNameContainingIgnoreCase(word);
    }

    public Project createProject(Project project) {

        // Asegurar que las listas no sean null
        if (project.getTechnologies() == null) {
            project.setTechnologies(new HashSet<>());
        }

        if (project.getDevelopers() == null) {
            project.setDevelopers(new HashSet<>());
        }

        return projectRepository.save(project);
    }

    public Project updateProject(Integer id, Project newProject) {

        return projectRepository.findById(id)
            .map(project -> {

                project.setName(newProject.getName());
                project.setDescription(newProject.getDescription());
                project.setStartDate(newProject.getStartDate());
                project.setEndDate(newProject.getEndDate());
                project.setDemoUrl(newProject.getDemoUrl());
                project.setPicture(newProject.getPicture());
                project.setStatus(newProject.getStatus());

                if (newProject.getTechnologies() == null) {
                    project.setTechnologies(new HashSet<>());
                } else {
                    project.setTechnologies(newProject.getTechnologies());
                }

                if (newProject.getDevelopers() == null) {
                    project.setDevelopers(new HashSet<>());
                } else {
                    project.setDevelopers(newProject.getDevelopers());
                }

                return projectRepository.save(project);
            })
            .orElse(null);
    }

    public boolean delete(Integer id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
